package org.jenkinsci.plugins.iosbuilder.bouncycastle.asn1.cms;

import org.jenkinsci.plugins.iosbuilder.bouncycastle.asn1.ASN1EncodableVector;
import org.jenkinsci.plugins.iosbuilder.bouncycastle.asn1.ASN1Object;
import org.jenkinsci.plugins.iosbuilder.bouncycastle.asn1.ASN1Primitive;
import org.jenkinsci.plugins.iosbuilder.bouncycastle.asn1.ASN1Sequence;
import org.jenkinsci.plugins.iosbuilder.bouncycastle.asn1.ASN1TaggedObject;
import org.jenkinsci.plugins.iosbuilder.bouncycastle.asn1.DERBitString;
import org.jenkinsci.plugins.iosbuilder.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.jenkinsci.plugins.iosbuilder.bouncycastle.asn1.*;


public class OriginatorPublicKey
    extends ASN1Object
{
    private AlgorithmIdentifier algorithm;
    private DERBitString publicKey;
    
    public OriginatorPublicKey(
        AlgorithmIdentifier algorithm,
        byte[]              publicKey)
    {
        this.algorithm = algorithm;
        this.publicKey = new DERBitString(publicKey);
    }
    
    public OriginatorPublicKey(
        ASN1Sequence seq)
    {
        algorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(0));
        publicKey = (DERBitString)seq.getObjectAt(1);
    }
    
    /**
     * return an OriginatorPublicKey object from a tagged object.
     *
     * @param obj the tagged object holding the object we want.
     * @param explicit true if the object is meant to be explicitly
     *              tagged false otherwise.
     * @exception IllegalArgumentException if the object held by the
     *          tagged object cannot be converted.
     */
    public static OriginatorPublicKey getInstance(
        ASN1TaggedObject    obj,
        boolean             explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }
    
    /**
     * return an OriginatorPublicKey object from the given object.
     *
     * @param obj the object we want converted.
     * @exception IllegalArgumentException if the object cannot be converted.
     */
    public static OriginatorPublicKey getInstance(
        Object obj)
    {
        if (obj == null || obj instanceof OriginatorPublicKey)
        {
            return (OriginatorPublicKey)obj;
        }
        
        if (obj instanceof ASN1Sequence)
        {
            return new OriginatorPublicKey((ASN1Sequence)obj);
        }
        
        throw new IllegalArgumentException("Invalid OriginatorPublicKey: " + obj.getClass().getName());
    } 

    public AlgorithmIdentifier getAlgorithm()
    {
        return algorithm;
    }

    public DERBitString getPublicKey()
    {
        return publicKey;
    }

    /** 
     * Produce an object suitable for an ASN1OutputStream.
     * <pre>
     * OriginatorPublicKey ::= SEQUENCE {
     *     algorithm AlgorithmIdentifier,
     *     publicKey BIT STRING 
     * }
     * </pre>
     */
    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();

        v.add(algorithm);
        v.add(publicKey);

        return new DERSequence(v);
    }
}
