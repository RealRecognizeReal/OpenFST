package core.parser;





import org.opencv.core.Core;
import org.opencv.highgui.VideoCapture;

import java.io.File;

/**
 * Created by waps12b on 2016. 11. 22..
 */
public class ImageRecognizer {

    static {
//        System.load("/Users/waps12b/IdeaProjects/OpenFST/lib/opencv/opencv-2413.jar");
//        System.load("/usr/local/Cellar/opencv/2.4.13.1/share/OpenCV/java/libopencv_java2413.dylib");
//        System.load("/usr/lib/libopencv_java2413.dylib");
//        System.loadLibrary( Core.NATIVE_LIBRARY_NAME);

//        System.out.println( Core.NATIVE_LIBRARY_NAME);
        nu.pattern.OpenCV.loadLocally();
        nu.pattern.OpenCV.loadShared();

    }


    public ImageRecognizer(File img)
    {

        VideoCapture capture = new VideoCapture(0);
    }

    public static void main(String[] args)
    {
        new ImageRecognizer(new File("resource/test/img/example1.jpg"));
    }
}


// c, c ++ // builder , compier // -> binary code