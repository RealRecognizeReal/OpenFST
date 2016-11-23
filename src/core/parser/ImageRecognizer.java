package core.parser;



import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import java.io.File;

/**
 * Created by waps12b on 2016. 11. 22..
 */
public class ImageRecognizer {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public ImageRecognizer(File img)
    {
        Mat mat = Highgui.imread(img.getAbsolutePath(), Highgui.CV_LOAD_IMAGE_ANYCOLOR);
    }

    public static void main(String[] args)
    {
        new ImageRecognizer(new File("resource/test/img/example1.jpg"));
    }
}


// c, c ++ // builder , compier // -> binary code