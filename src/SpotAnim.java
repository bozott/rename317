// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public final class SpotAnim {

    public static void unpackConfig(JagexArchive jagexArchive)
    {
        Stream stream = new Stream(jagexArchive.getDataForName("spotanim.dat"));
        int length = stream.readUnsignedWord();
        if(cache == null)
            cache = new SpotAnim[length];
        for(int j = 0; j < length; j++)
        {
            if(cache[j] == null)
                cache[j] = new SpotAnim();
            cache[j].id = j;
            cache[j].readValues(stream);
        }

    }

    private void readValues(Stream stream)
    {
        do
        {
            int i = stream.readUnsignedByte();
            if(i == 0)
                return;
            if(i == 1)
                modelID = stream.readUnsignedWord();
            else
            if(i == 2)
            {
                animationID = stream.readUnsignedWord();
                if(Animation.anims != null)
                    aAnimation_407 = Animation.anims[animationID];
            } else
            if(i == 4)
                resizeXY = stream.readUnsignedWord();
            else
            if(i == 5)
                resizeZ = stream.readUnsignedWord();
            else
            if(i == 6)
                rotation = stream.readUnsignedWord();
            else
            if(i == 7)
                modelBrightness = stream.readUnsignedByte();
            else
            if(i == 8)
                modelShadow = stream.readUnsignedByte();
            else
            if(i >= 40 && i < 50)
                originalModelColours[i - 40] = stream.readUnsignedWord();
            else
            if(i >= 50 && i < 60)
                modifiedModelColours[i - 50] = stream.readUnsignedWord();
            else
                System.out.println("Error unrecognised spotanim config code: " + i);
        } while(true);
    }

    public Model getModel()
    {
        Model model = (Model) aMRUNodes_415.get(id);
        if(model != null)
            return model;
        model = Model.getModel(modelID);
        if(model == null)
            return null;
        for(int i = 0; i < 6; i++)
            if(originalModelColours[0] != 0)
                model.recolour(originalModelColours[i], modifiedModelColours[i]);

        aMRUNodes_415.put(model, id);
        return model;
    }

    private SpotAnim()
    {
        animationID = -1;
        originalModelColours = new int[6];
        modifiedModelColours = new int[6];
        resizeXY = 128;
        resizeZ = 128;
    }

    public static SpotAnim cache[];
    private int id;
    private int modelID;
    private int animationID;
    public Animation aAnimation_407;
    private final int[] originalModelColours;
    private final int[] modifiedModelColours;
    public int resizeXY;
    public int resizeZ;
    public int rotation;
    public int modelBrightness;
    public int modelShadow;
    public static MRUNodes aMRUNodes_415 = new MRUNodes(30);

}