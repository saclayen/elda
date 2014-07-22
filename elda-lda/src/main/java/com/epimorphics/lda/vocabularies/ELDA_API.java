/* CVS $Id: $ */
package com.epimorphics.lda.vocabularies; 
import com.hp.hpl.jena.rdf.model.*;
 
/**
 * Vocabulary definitions from /home/chris/EldaThings/elda-in-github/elda-lda/../vocabs/elda_api.ttl 
 * @author Auto-generated by schemagen on 22 Jul 2014 15:31 
 */
public class ELDA_API  extends API {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://www.epimorphics.com/vocabularies/lda#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    /** <p>:</p> */
    public static final Property allowReserved = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#allowReserved" );
    
    /** <p></p> */
    public static final Property allowSyntaxProperties = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#allowSyntaxProperties" );
    
    /** <p>comma-separated list of file wildcarded paths for authentication files.</p> */
    public static final Property authFile = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#authFile" );
    
    /** <p>key identifying which authentication item to use.</p> */
    public static final Property authKey = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#authKey" );
    
    /** <p>If given a value, that is the cache expiry time in seconds for an endpoint 
     *  or family of endpoints.</p>
     */
    public static final Property cacheExpiryTime = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#cacheExpiryTime" );
    
    /** <p>name of one of Elda's cache policies.</p> */
    public static final Property cachePolicyName = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#cachePolicyName" );
    
    /** <p>full Java class name of an Elda formatter plugin.</p> */
    public static final Property className = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#className" );
    
    /** <p></p> */
    public static final Property construct = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#construct" );
    
    /** <p></p> */
    public static final Property describeAllLabel = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#describeAllLabel" );
    
    /** <p></p> */
    public static final Property describeThreshold = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#describeThreshold" );
    
    /** <p></p> */
    public static final Property element = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#element" );
    
    /** <p>If true, "true", or "yes", Elda will generate a total item count for any list-endpoint 
     *  query. If false, "false", or "no", it will not. Otherwise counting is enabled 
     *  using the _count query parameter.</p>
     */
    public static final Property enableCounting = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#enableCounting" );
    
    /** <p>If given the value true, then Elda generates an ETag for its results.</p> */
    public static final Property enableETags = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#enableETags" );
    
    /** <p></p> */
    public static final Property enhanceViewWith = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#enhanceViewWith" );
    
    /** <p></p> */
    public static final Property feedAuthorProperties = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#feedAuthorProperties" );
    
    /** <p></p> */
    public static final Property feedAuthors = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#feedAuthors" );
    
    /** <p></p> */
    public static final Property feedDateProperties = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#feedDateProperties" );
    
    /** <p></p> */
    public static final Property feedLabelProperties = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#feedLabelProperties" );
    
    /** <p></p> */
    public static final Property feedNamespace = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#feedNamespace" );
    
    /** <p></p> */
    public static final Property feedRights = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#feedRights" );
    
    /** <p></p> */
    public static final Property feedRightsProperties = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#feedRightsProperties" );
    
    /** <p></p> */
    public static final Property feedTitle = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#feedTitle" );
    
    /** <p>Configuration property to set the name of the queried graph from an endpoint-specific 
     *  template.</p>
     */
    public static final Property graphTemplate = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#graphTemplate" );
    
    /** <p></p> */
    public static final Property ifStarts = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#ifStarts" );
    
    /** <p></p> */
    public static final Property listURL = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#listURL" );
    
    /** <p></p> */
    public static final Property loadedFrom = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#loadedFrom" );
    
    /** <p></p> */
    public static final Property match = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#match" );
    
    /** <p></p> */
    public static final Property metaURL = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#metaURL" );
    
    /** <p></p> */
    public static final Property metadataOptions = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#metadataOptions" );
    
    /** <p></p> */
    public static final Property replaceStartBy = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#replaceStartBy" );
    
    /** <p></p> */
    public static final Property rewriteResultURIs = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#rewriteResultURIs" );
    
    /** <p></p> */
    public static final Property shortnameMode = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#shortnameMode" );
    
    /** <p></p> */
    public static final Property sparqlQuery = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#sparqlQuery" );
    
    /** <p></p> */
    public static final Property supportsNestedSelect = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#supportsNestedSelect" );
    
    /** <p></p> */
    public static final Property textContentProperty = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#textContentProperty" );
    
    public static final Property textPlaceEarly = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#textPlaceEarly" );
    
    /** <p></p> */
    public static final Property textQueryProperty = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#textQueryProperty" );
    
    /** <p></p> */
    public static final Property textSearchOperand = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#textSearchOperand" );
    
    /** <p></p> */
    public static final Property uriTemplatePrefix = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#uriTemplatePrefix" );
    
    /** <p></p> */
    public static final Property velocityTemplate = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#velocityTemplate" );
    
    /** <p></p> */
    public static final Property viewName = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#viewName" );
    
    /** <p></p> */
    public static final Property wantsContext = m_model.createProperty( "http://www.epimorphics.com/vocabularies/lda#wantsContext" );
    
    /** <p></p> */
    public static final Resource Combiner = m_model.createResource( "http://www.epimorphics.com/vocabularies/lda#Combiner" );
    
    /** <p></p> */
    public static final Resource FeedFormatter = m_model.createResource( "http://www.epimorphics.com/vocabularies/lda#FeedFormatter" );
    
    /** <p></p> */
    public static final Resource VelocityFormatter = m_model.createResource( "http://www.epimorphics.com/vocabularies/lda#VelocityFormatter" );
    
    /** <p></p> */
    public static final Resource preferLocalnames = m_model.createResource( "http://www.epimorphics.com/vocabularies/lda#preferLocalnames" );
    
    /** <p></p> */
    public static final Resource preferPrefixes = m_model.createResource( "http://www.epimorphics.com/vocabularies/lda#preferPrefixes" );
    
    /** <p></p> */
    public static final Resource roundTrip = m_model.createResource( "http://www.epimorphics.com/vocabularies/lda#roundTrip" );
    
}
