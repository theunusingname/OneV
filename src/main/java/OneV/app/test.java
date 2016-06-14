package OneV.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * Created by kkuznetsov on 27.04.2016.
 */
public class test {
    public static void main(String[] args) {

        FileOutputStream fos;
        File outFile;
        int aggregatedIntAAA=0;
        float aggregatedFloatAAA=0;
        int aggregatedIntBBB=0;
        float aggregatedFloatBBB=0;
        float aggregatedFloatAAAstreamInt[]={0,0,0};
        float aggregatedFloatBBBstreamInt[]={0,0,0};
        float aggregatedFloatAAAstreamVar[]={0,0,0};
        float aggregatedFloatBBBstreamVar[]={0,0,0};
        float aggregatedFloatAAAstreamFloat[]={0,0,0};
        float aggregatedFloatBBBstreamFloat[]={0,0,0};
        float aggregatedFloatAAAstreamDate[]={0,0,0};
        float aggregatedFloatBBBstreamDate[]={0,0,0};
        String varcharVariants[]={"aaa","bbb","ccc"};
        String streamVarchVariants[]={"a","b","c"};
        String streamFloatVariants[]={"0.0","0.1","0.2"};
        Date streamDateVariantes[]={new Date(86400000),new Date(172800000),new Date(345600000)};

        int hash=0;

        if(args.length<2)
        {
            System.out.println("need args");
            return;
        }

        try {
            outFile = new File(args[0]);
            fos = new FileOutputStream(outFile);
            char[] timeTag=args[1].toCharArray();
            for(int i=0;i<timeTag.length;i++)
            {
                hash +=(int)timeTag[i];
                hash-=(hash << 13) | (hash >> 19);
            }

            Random rnd=new Random(hash);
            for(int i=0;i<20;i++)
            {
                if (i!=0) fos.write("\n".getBytes());
                String nextVarchVal=varcharVariants[rnd.nextInt(3)];
                int nextIntVal=rnd.nextInt(1000);
                Float nextFloat =rnd.nextFloat();
                String nextFloatVal= nextFloat.toString();
                nextFloatVal= nextFloatVal.substring(0,4);
                int nextStreamInt =rnd.nextInt(3)+1;
                String nextStreamVarch=streamVarchVariants[rnd.nextInt(3)];
                String nextStreamFloat=streamFloatVariants[rnd.nextInt(3)];
                Date nextStreamDate=streamDateVariantes[rnd.nextInt(3)];

                fos.write((nextVarchVal+"\t"+nextIntVal+"\t"+nextFloatVal+"\t"+nextStreamInt+"\t"
                        +nextStreamVarch+"\t"+nextStreamFloat +"\t"+nextStreamDate.toLocaleString()).getBytes());

                aggregatedFloatAAA+=new Float(nextFloatVal);
                aggregatedIntAAA+=nextIntVal;

                if(nextVarchVal=="aaa"){}



            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
