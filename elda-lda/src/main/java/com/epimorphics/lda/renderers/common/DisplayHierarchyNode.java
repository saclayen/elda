/*****************************************************************************
 * Elda project https://github.com/epimorphics/elda
 * LDA spec: http://code.google.com/p/linked-data-api/
 *
 * Copyright (c) 2014 Epimorphics Ltd. All rights reserved.
 * Licensed under the Apache Software License 2.0.
 * Full license: https://raw.githubusercontent.com/epimorphics/elda/master/LICENCE
 *****************************************************************************/

package com.epimorphics.lda.renderers.common;


import java.util.*;

import com.epimorphics.lda.renderers.common.DisplayHierarchy.DisplayHierarchyContext;


/**
 *  A node in the unfolded display hierarchy starting at the item roots.
 */
public class DisplayHierarchyNode {
    /***********************************/
    /* Constants                       */
    /***********************************/

    /***********************************/
    /* Static variables                */
    /***********************************/

    /***********************************/
    /* Instance variables              */
    /***********************************/

    /** The path leading to this node */
    private PropertyPath pathTo;

    /** For nodes that are part of an explicit property path */
    private Set<PropertyPath> explicitPaths = new HashSet<PropertyPath>();

    /** The parent node, null for root nodes */
    private DisplayHierarchyNode parent;

    /** The RDF node that is being presented at this level */
    DisplayRdfNode rdfNode;

    /** The list of child nodes of this node */
    private List<DisplayHierarchyNode> children = new ArrayList<DisplayHierarchyNode>();

    /***********************************/
    /* Constructors                    */
    /***********************************/

    public DisplayHierarchyNode( PropertyPath pathTo, DisplayHierarchyNode parent, DisplayRdfNode rdfNode ) {
        this.pathTo = pathTo;
        this.parent = parent;
        this.rdfNode = rdfNode;

        if (parent != null) {
            parent.children().add( this );
        }
    }

    /***********************************/
    /* External signature methods      */
    /***********************************/

    /** @return The path to this node */
    public PropertyPath pathTo() {
        return pathTo;
    }

    /** @return True if this node is explicitly on a property path */
    public boolean isOnExplicitPath() {
        return !explicitPaths.isEmpty();
    }

    /** @return The explicit property path that this expansion is following */
    public Set<PropertyPath> explicitPaths() {
        return explicitPaths;
    }

    /** @return The parent node */
    public DisplayHierarchyNode parent() {
        return parent;
    }

    /** @return True if this node is a root of the hierarchy */
    public boolean isRoot() {
        return parent == null;
    }

    /** @return The RDF node that this hierarchy node is presenting */
    public DisplayRdfNode rdfNode() {
        return rdfNode;
    }

    /** @return True if this resource has already been seen on this branch */
    public boolean isLoop() {
        return (parent() != null) ? parent().findAncestor( rdfNode() ) : false;
    }

    /** @return True if this node's RDF node equals <code>resource</code>, or if any of my ancestors do */
    public boolean findAncestor( DisplayRdfNode resource ) {
        if (rdfNode().equals( resource )) {
            return true;
        }
        else if (parent() != null) {
            return parent().findAncestor( resource );
        }
        else {
            return false;
        }
    }

    /**
     * A node is a leaf of the hierarchy if any of the following apply:
     * <ul><li>it is a literal</li>
     *     <li>it has already occurred among its own ancestors (ie is a loop)</li>
     *     <li>it is not a top-level root node, has been previously expanded
     *         and is not on an explicit property path</li>
     * </ul>
     * @param context The current context, which contains the list of seen nodes
     * @return True if this is a leaf node of this hierarchy
     */
    public boolean isLeaf( DisplayHierarchyContext context ) {
        return rdfNode().isLiteral() ||
               isLoop() ||
               (!isRoot() &&
                !isOnExplicitPath() &&
                context.isSeen( rdfNode() ));
    }

    /** @return The list of children of this node */
    public List<DisplayHierarchyNode> children() {
        return children;
    }

    /***********************************/
    /* Internal implementation methods */
    /***********************************/

    /***********************************/
    /* Inner class definitions         */
    /***********************************/

}