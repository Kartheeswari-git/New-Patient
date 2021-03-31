package HospitalPortalFlow;

import java.io.File;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Imp {
	
	
	
	
	public void sikuli() {
	// This is used to click the open button of chrome/firefox browser upon the screenshot captured	
		Screen s=new Screen();
		Pattern file_input=new Pattern("C:\\Users\\Ajith\\Documents\\For Doc upload\\filename.png");
		Pattern open_button=new Pattern("C:\\Users\\Ajith\\Documents\\For Doc upload\\open.png");//			s.wait(file_input,20);
	//	s.click(file_input);
	//	s.type(file_input,Path);
	//	s.wait(open_button,20);
	//		s.doubleClick(open_button);
	}
	
	public void AutoIt() {
	//	Here we need to write the script to paste the file path and then clicks enter (should be static)
//	Runtime.getRuntime().exec("C:\\Users\\Ajith\\Documents\\HP_fileuploadscript.exe");
	}
}
