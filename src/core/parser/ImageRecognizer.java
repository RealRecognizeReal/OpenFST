package core.parser;



import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import java.io.File;

/**
 * Created by waps12b on 2016. 11. 22..
 */
public class ImageRecognizer {

    static {
//        System.load("/Users/waps12b/IdeaProjects/OpenFST/lib/opencv/opencv-2413.jar");
        System.load("/Users/waps12b/IdeaProjects/OpenFST/lib/opencv/libopencv_java2413.dylib");
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public ImageRecognizer(File img)
    {
        Mat mat = Mat.eye(1,1, CvType.CV_8UC1);
    }

    public static void main(String[] args)
    {
        new ImageRecognizer(new File("resource/test/img/example1.jpg"));
    }
}


// c, c ++ // builder , compier // -> binary code