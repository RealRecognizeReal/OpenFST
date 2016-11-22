package core.parser;
import org.opencv.core.*;
import org.opencv.highgui.Highgui;

import java.io.File;

/**
 * Created by waps12b on 2016. 11. 22..
 */
public class ImageRecognizer {

    public ImageRecognizer(File img)
    {
        Mat mat = Highgui.imread(img.getAbsolutePath(), 0);


    }


    public static void main(String[] args)
    {
        new ImageRecognizer(new File("resource/test/img/example1.jpg"));
    }
}
