/*
    See lda-top/LICENCE (or http://elda.googlecode.com/hg/LICENCE)
    for the licence for this software.
    
    (c) Copyright 2011 Epimorphics Limited
    $Id$
*/

/******************************************************************
    File:        SpecManagerImpl.java
    Created by:  Dave Reynolds
    Created on:  7 Feb 2010
 * 
 * (c) Copyright 2010, Epimorphics Limited
 * $Id:  $
 *****************************************************************/

package com.epimorphics.lda.specmanager;

import static com.epimorphics.lda.specmanager.SpecUtils.*;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epimorphics.lda.core.APIEndpoint;
import com.epimorphics.lda.core.APIFactory;
import com.epimorphics.lda.core.ModelLoader;
import com.epimorphics.lda.exceptions.APISecurityException;
import com.epimorphics.lda.routing.Match;
import com.epimorphics.lda.routing.Router;
import com.epimorphics.lda.specs.APIEndpointSpec;
import com.epimorphics.lda.specs.APISpec;
import com.epimorphics.lda.support.MultiMap;
import com.epimorphics.lda.vocabularies.EXTRAS;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;

/**
 * Implementation of SpecManager for simple non-GAE environment.
 * This version does not persist the specifications at all beyond
 * the webapp lifetime.
 * 
 * @author <a href="mailto:der@epimorphics.com">Dave Reynolds</a>
 * @version $Revision: $
 */
public class SpecManagerImpl implements SpecManager {

    static Logger log = LoggerFactory.getLogger(SpecManagerImpl.class);
    
    protected Router router;
    protected ModelLoader modelLoader;
    
    protected Map<String, SpecEntry> specs = new HashMap<String, SpecEntry>();
    
    public SpecManagerImpl(Router router, ModelLoader modelLoader) {
        this.router = router;
        this.modelLoader = modelLoader;
    }
    
    @Override public APISpec addSpec(String uri, String key, Model spec) throws APISecurityException {
        if (specs.containsKey(uri)) {
            return updateSpec(uri, key, spec);
        } else {
            log.info("Creating API spec at: " + uri);
            Resource specRoot = spec.getResource(uri);
            String prePath = getPrePath( specRoot );
			APISpec apiSpec = new APISpec( FileManager.get(), specRoot, modelLoader );
            synchronized (specs) { specs.put(uri, new SpecEntry(uri, key, apiSpec, spec)); }
            if (prePath.length() > 0) log.info( "prePath defined: " + prePath );
            APIFactory.registerApi( withPrePath(router, prePath), apiSpec );
            return apiSpec;
        }
    }

    private Router withPrePath( final Router r, final String prePath ) {
		return new Router() {

			@Override public void register(String URITemplate, APIEndpoint api) {
				r.register( prePath + URITemplate, api );
			}

			@Override public void unregister(String URITemplate) {
				r.unregister( prePath + URITemplate );
			}

			@Override public Match getMatch(String path, MultiMap<String, String> queryParams) {
				return r.getMatch( path, queryParams );
			}

			@Override public List<String> templates() {
				return r.templates();
			}

			@Override public String findItemURIPath(URI requestURI, String itemPath) {
				return r.findItemURIPath( requestURI, itemPath );
			}
			
		};
	}

	private String getPrePath( Resource specRoot ) {
    	Statement x = specRoot.getProperty( EXTRAS.prePath );
    	if (x != null) return x.getString();
    	Statement y = specRoot.getProperty(EXTRAS.loadedFrom);
    	if (y != null) return "/" + new File(y.getString()).getName().replace(".ttl", "");
		return "";
	}

	@Override public void deleteSpec(String uri, String key) throws APISecurityException {
        SpecEntry entry = specs.get(uri);
        if (entry == null) {
            // no error if nothing to delete so we can use update safely for create
            return;
        }
        if (! keyMatches(uri, key, entry.keyDigest)) {
            throw new APISecurityException("This key is not permited to modify API " + uri);
        }
        log.info("Delete API sepc: " + uri);
        for (APIEndpointSpec eps : entry.spec.getEndpoints()) {
            router.unregister(eps.getURITemplate());
        }
        synchronized (specs) {
            specs.remove(uri);
        }
    }

    @Override public void loadSpecFor(String uriRequest) {
        // Nothing to do in this environment,  all known specs are permanently loaded
    }

    @Override public APISpec updateSpec(String uri, String key, Model spec) throws APISecurityException {
        log.info("Udating spec: " + uri);
        deleteSpec(uri, key);
        return addSpec(uri, key, spec);
    }

    @Override public Model getSpecForAPI(String api) {
        SpecEntry entry = specs.get(api);
        if (entry != null) 
            return entry.model;
        return null;
    }

    @Override public Model getSpecForEndpoint(String url) {
        Match match = router.getMatch(url, new MultiMap<String, String>() );
        if (match != null) {
            String apiURI = match.getEndpoint().getSpec().getAPISpec().getSpecURI();
            return getSpecForAPI(apiURI);
        } 
        return null;
    }

	@Override synchronized public List<SpecEntry> allSpecs() {
		List<SpecEntry> result = new ArrayList<SpecEntry>();
		for (Map.Entry<String, SpecEntry> e: specs.entrySet() ) result.add( e.getValue() );
		return result;
	}

}

