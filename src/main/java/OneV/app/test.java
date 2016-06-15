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
        System.out.println((double) 99/10);
        FileOutputStream fos;
        File outFile;
        int aggregatedGlobalInt=0;
        float aggregatedGlobalFloat=0;
        int aggregatedInt[]={0,0,0};
        double aggregatedFloat[]={0,0,0};

        double aggregatedFloatStreamInt[][]={{0,0,0},{0,0,0},{0,0,0}};
        double aggregatedFloatStreamVar[][]={{0,0,0},{0,0,0},{0,0,0}};
        double aggregatedFloatStreamFloat[][]={{0,0,0},{0,0,0},{0,0,0}};
        double aggregatedFloatStreamDate[][]={{0,0,0},{0,0,0},{0,0,0}};

        String varcharVariants[]={"aaa","bbb","ccc"};
        int streamIntVariants[]={1,2,3};
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
            for(int i=0;i<200;i++)
            {
                if (i!=0) fos.write("\n".getBytes());
                String nextVarchVal=varcharVariants[rnd.nextInt(3)];
                int nextIntVal=i;
                double nextFloat = (double) rnd.nextInt(100)/10;
                int nextStreamInt =streamIntVariants[rnd.nextInt(3)];
                String nextStreamVarch=streamVarchVariants[rnd.nextInt(3)];
                String nextStreamFloat=streamFloatVariants[rnd.nextInt(3)];
                Date nextStreamDate=streamDateVariantes[rnd.nextInt(3)];

                fos.write((nextVarchVal+"\t"+nextIntVal+"\t"+nextFloat+"\t"+nextStreamInt+"\t"
                        +nextStreamVarch+"\t"+nextStreamFloat +"\t"+nextStreamDate.toLocaleString().substring(0,10)).getBytes());

                aggregatedGlobalFloat+=nextFloat;
                aggregatedGlobalInt+=nextIntVal;

                for (int j=0;j<3;j++) {                                                             //j-details k-stream
                    if (nextVarchVal == varcharVariants[j]) {
                        aggregatedFloat[j] += nextFloat;
                        for (int k = 0; k < 3; k++) {
                            if (nextStreamDate == streamDateVariantes[k])
                                aggregatedFloatStreamDate[k][j] += nextFloat;

                            if (nextStreamFloat == streamFloatVariants[k])
                                aggregatedFloatStreamFloat[k][j] += nextFloat;

                            if (nextStreamInt == streamIntVariants[k])
                                aggregatedFloatStreamInt[k][j] += nextFloat;

                            if (nextStreamVarch == streamVarchVariants[k])
                                aggregatedFloatStreamVar[k][j] += nextFloat;
                        }
                    }
                }

            }

            fos.close();
            File resultByDetails=new File("ResultByDetails");
            FileOutputStream resiltByDetailsOutStr=new FileOutputStream(resultByDetails);

            for(int k=0;k<3;k++)
            {
                resiltByDetailsOutStr.write((varcharVariants[k]+"\t"+new Double( aggregatedFloat[k]).floatValue()+"\n").getBytes());

                File resultVarchStream=new File("resultVarchStream"+"_"+k);
                FileOutputStream resultByVarchOut=new FileOutputStream(resultVarchStream);
                File resultDateStream=new File("resultDateStream"+"_"+k);
                FileOutputStream resultByDateOut=new FileOutputStream(resultDateStream);
                File resultFloatStream=new File("resultFloatStream"+"_"+k);
                FileOutputStream resultByFloatOut=new FileOutputStream(resultFloatStream);
                File resultIntStream=new File("resultIntStream"+"_"+k);
                FileOutputStream resultByIntOut=new FileOutputStream(resultIntStream);

                for(int j=0;j<3;j++)
                {
                    resultByVarchOut.write((varcharVariants[j]+"\t"+new Double( aggregatedFloatStreamVar[k][j]).floatValue()+"\n").getBytes());
                    resultByDateOut.write((varcharVariants[j]+"\t"+ new Double( aggregatedFloatStreamDate[k][j]).floatValue()+"\n").getBytes());
                    resultByFloatOut.write((varcharVariants[j]+"\t"+ new Double( aggregatedFloatStreamFloat[k][j]).floatValue()+"\n").getBytes());
                    resultByIntOut.write((varcharVariants[j]+"\t"+new Double( aggregatedFloatStreamInt[k][j]).floatValue()+"\n").getBytes());
                }
                resultByDateOut.close();
                resultByFloatOut.close();
                resultByIntOut.close();
                resultByVarchOut.close();

            }
            resiltByDetailsOutStr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
