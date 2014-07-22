/*
    See lda-top/LICENCE (or https://raw.github.com/epimorphics/elda/master/LICENCE)
    for the licence for this software.
    
    (c) Copyright 2014 Epimorphics Limited
    $Id$
*/

package com.epimorphics.lda.specs;

import java.util.*;

import com.epimorphics.lda.vocabularies.ELDA_API;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.shared.BrokenException;

public class MetadataOptions {
    
	/**
	    If there are any statements (R EXTRAS.metadataOptions S) then clear
	    <code>metadataOptions</code> and then add all the comma-separated
	    components of all the S's lowercased.
	*/
    public static void extract( Set<String> metadataOptions, Resource R ) {
    	List<Statement> options = R.listProperties( ELDA_API.metadataOptions ).toList();
    	if (options.size() > 0) {
    		metadataOptions.clear();
    		for (Statement os: options)
    			set( metadataOptions, os.getString() );
    	}
    }

    /**
        Add to <code>metadataOptions</code> lower-case versions of the comma-
        separated elements of <code>mdo</code>.
    */
	public static void set( Set<String> metadataOptions, String mdo ) {
		if (mdo.length() > 0)
			for (String option: mdo.split( " *, *" ))
				metadataOptions.add( option.toLowerCase() );
	}

	public static String[] get( Resource R ) {
		List<String> result = new ArrayList<String>();
		
		if (R == null) throw new BrokenException(">> OOPSY-DAISY.");
		
		List<Statement> options = R.listProperties( ELDA_API.metadataOptions ).toList();
    	if (options.size() > 0) 
    		for (Statement os: options)
    			for (String opt: os.getString().split( " *, *" ))
    				result.add( opt.toLowerCase() );
		return result.toArray( new String[result.size()]);
	}
}