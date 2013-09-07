/* -*- mode: java; c-basic-offset: 4; tab-width: 4; indent-tabs-mode: nil -*- */
/*
 * Copyright 2013 Real Logic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.sbe;

import org.w3c.dom.Node;

/**
 * An SBE type. One of encodedDataType, compositeType, enumType, or setType per the SBE spec.
 */
public class Type
{
    /** Default presence attribute for Types */
    public static final String DEFAULT_PRESENCE = "required";

    /** Name of the type. Used in the types map */
    private final String name;
    /** Presence requirement for the type */
    private final Presence presence;
    /** descrption of the type. */
    private final String description;
    /** fixUsage of the type. */
    private final String fixUsage;

    /**
     * NOTE: additional constructors can be used for additional parsers
     */

    /**
     * Construct a new Type from XML Schema. Called by subclasses to mostly set common fields
     *
     * @param node from the XML Schema Parsing
     */
    public Type(final Node node)
    {
	/** grab common field schema attributes
	 * - name (required)
	 * - presence (required by XSD to provide default)
	 * - fixUsage (optional - must be in type or message field)
	 * - description (optional)
	 */
	this.name = XmlSchemaParser.getXmlAttributeValue(node, "name");
	// The schema should set default, so "presence" should always be available, but let's set a default
	this.presence = Presence.lookup(XmlSchemaParser.getXmlAttributeValue(node, "presence", "required"));
	this.description = XmlSchemaParser.getXmlAttributeValueNullable(node, "description");
	this.fixUsage = XmlSchemaParser.getXmlAttributeValueNullable(node, "fixUsage");
    }

    /**
     * Return the name of the type
     *
     * @return name of the Type
     */
    public String getName()
    {
        return name;
    }

    /**
     * Return the presence of the type
     *
     * @return presence of the Type
     */
    public Presence getPresence()
    {
        return presence;
    }

    /**
     * The size (in octets) of the Type
     */
    public int size()
    {
        // TODO actually delegate this to subtypes
        return 0;
    }
}