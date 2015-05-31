import java.lang.reflect.Array;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

//import javafx.scene.shape.DrawMode;

import java.util.Vector;

import org.opencv.core.Core;
import org.opencv.*;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;

public class MainClass {
	
	static {
		try {
			System.loadLibrary("opencv_java2410");
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		try{
			String filename = "picture/testImage6.jpg";
			Mat image = Highgui.imread(filename);
			
			// lay vung chu
			Rect textArea = JT_ImageProccessor.extractTextArea(image); // lay toa do vung chu
			Mat croppedImage = new Mat(image, textArea); // Cat anh
			Highgui.imwrite("picture/testImag6_textArea.jpg", croppedImage); // write ra anh
			
			// lay cac dong
			List<Rect> textLines = new ArrayList<Rect>();
			textLines = JT_ImageProccessor.extractTextLine(image);
			
			       // khoanh cac dong tren anh
			Mat image2 = image.clone();
			for(int i = 0; i < textLines.size(); ++i) {
				Rect rect = textLines.get(i);
				Point p1 = new Point(rect.x, rect.y);
				Point p2 = new Point(rect.x + rect.width, rect.y + rect.height);
				Core.rectangle(image2, p1, p2, new Scalar(255, 0, 0));
			}
			       // write ra anh
			Highgui.imwrite("picture/testImage6_textLines.jpg", image2);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
