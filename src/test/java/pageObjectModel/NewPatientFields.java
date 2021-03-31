package pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewPatientFields {

	public static WebDriver driver;
	
	
	
	public static WebElement new_patient_tab(WebDriver driver) {
		return driver.findElement(By.partialLinkText("New Patie"));
	}
	//search
	public static WebElement HealthCare_ID(WebDriver driver) {
		return driver.findElement(By.xpath("//input[@name='healthCard']"));
	}
	public static WebElement search_button(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"collapseThree\"]/div/div/div/div[1]/div/div[2]/button[1]"));
	}
	public static WebElement search_value(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"collapseThree\"]/div/div/div/div[2]/div/div[1]/div/div[2]/table/tbody/tr/td[3]/span/span/a"));
	}
	//Claim Details
	public static WebElement Claim_Details(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"headingOne\"]/h2/button"));
	}
	public static WebElement health_num(WebDriver driver) {
		return driver.findElement(By.xpath("//input[@name='healthCardNo']"));
	}
	public static WebElement Patient_name(WebDriver driver) {
		return driver.findElement(By.xpath("//input[@name='nameOfPatient']"));
	}
	public static WebElement DOA(WebDriver driver) {
		return driver.findElement(By.xpath("//input[@name='dateOfAdmission']"));
	}
	public static WebElement year(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"undefined-picker-container-DatePicker\"]/div/div[1]/div[2]/span[2]/button/span[2]"));
	}
	public static WebElement year_value(WebDriver driver) {
		return driver.findElement(By.xpath("//*[text(),'2020']"));
	}
	public static WebElement month(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"undefined-picker-container-DatePicker\"]/div/div[1]/div[2]/span[1]/button/span[2]"));
	}
	public static WebElement date(WebDriver driver) {
		return driver.findElement(By.xpath("//*[contains(text(),'12')]"));
	}
	public static WebElement hour(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[1]/div/button[11]/span[2]"));
	}
	public static WebElement min(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[2]/div/button[50]/span[2]"));
	}
	public static WebElement meridian(WebDriver driver) {
		return driver.findElement(By.xpath("//*[contains(text(),${meridian})]"));
	}
	
	
	public static WebElement DOD(WebDriver driver) {
		return driver.findElement(By.xpath("//input[contains(@name,'expectedDod')]"));
	}
	public static WebElement IPD_Patient_num(WebDriver driver) {
		return driver.findElement(By.xpath("//input[contains(@name,'patientId')]"));
	}
	public static WebElement Total_Estimated_Bill (WebDriver driver) {
		return driver.findElement(By.xpath("//input[contains(@name,'hospitalizationCost')]"));
	}
	public static WebElement Provisional_Diagnosis (WebDriver driver) {
		return driver.findElement(By.xpath("//input[contains(@name,'provisionalDiagnosis')]"));
	}
	public static WebElement Patient_Contact_Number (WebDriver driver) {
		return driver.findElement(By.xpath("//input[contains(@name,'primaryContact')]"));
	}
	public static WebElement Attendant_Contact_Number (WebDriver driver) {
		return driver.findElement(By.xpath("//input[contains(@name,'attendantContact')]"));
	}
	public static WebElement Name_of_the_Treating_Doctor (WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"collapseOne\"]/div/div/div[1]/div[2]/div/div[4]/div/div/input"));
	}
	public static WebElement Room_Type (WebDriver driver) {
		return driver.findElement(By.xpath("//select[contains(@name,'roomType')]"));
	}
	public static WebElement Room_Type_others (WebDriver driver) {
		return driver.findElement(By.xpath("//input[contains(@name,'roomTypeCode')]"));
	}
	public static WebElement email (WebDriver driver) {
		return driver.findElement(By.xpath("//input[contains(@name,'email')]"));
	}
	public static WebElement Next (WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"nextNav1\"]"));
	}
	
	//Doc Details
		public static WebElement Doc_Details(WebDriver driver) {
			return driver.findElement(By.xpath("//*[@id=\"headingTwo\"]/h2/button"));
		}
		public static WebElement preauth_value(WebDriver driver) {
			return driver.findElement(By.xpath("//*[contains(@name,'documentType100')]"));
		}
		public static WebElement add_button(WebDriver driver) {

		return driver.findElement(By.xpath("//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']"));
		}
		public static WebElement upload (WebDriver driver) {
			return driver.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]"));
		}
		public static WebElement ok (WebDriver driver) {
			return driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
		}

		//Adv_Search
		public static WebElement individual (WebDriver driver) {
			return driver.findElement(By.xpath("//input[contains(@value, 'Retail')]"));
		}
		public static WebElement grp (WebDriver driver) {
			return driver.findElement(By.xpath("//input[contains(@value, 'GMC')]"));
		}
		public static WebElement policy_num (WebDriver driver) {
			return driver.findElement(By.xpath("//input[contains(@name, 'policyNumber')]"));
		}

		public static WebElement Corp_name (WebDriver driver) {
			return driver.findElement(By.xpath("//input[contains(@name, 'corporateName')]"));
		}
		public static WebElement emp_code (WebDriver driver) {
			return driver.findElement(By.xpath("//input[contains(@name, 'empCode')]"));
		}
		public static WebElement Adv_search (WebDriver driver) {
			return driver.findElement(By.xpath("//*[@id=\"collapseThree\"]/div/div/div/div[1]/div[2]/div[3]/div/div[5]/button[1]"));
		}
		public static WebElement Adv_search_result (WebDriver driver) {
			return driver.findElement(By.xpath("//*[@id=\"collapseThree\"]/div/div/div/div[2]/div/div[1]/div/div[2]/table/tbody/tr/td[4]/span/span/a"));
		}
		public static WebElement Advanced_search (WebDriver driver) {
			return driver.findElement(By.partialLinkText("Advanced Sear"));
		}
		
		//Submit
		public static WebElement submit(WebDriver driver) {
			return driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		}
	//Message
		public static WebElement policy_xpiry(WebDriver driver) {
			return driver.findElement(By.xpath("//div[contains(text(),'Date of Admission is greater than Policy Expiry date')]"));
		}
		public static WebElement uni_search(WebDriver driver) {
			return driver.findElement(By.xpath("//input[contains(@placeholder,'Universal Search')]"));
		}
		public static WebElement uni_search_icon(WebDriver driver) {
			return driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div[1]/div/div/div/div/div/div/div[2]/div[1]/div/div[3]/form/div/div/button"));
		}
		public static WebElement ref_numclick(WebDriver driver) {
			return driver.findElement(By.xpath("//*[contains(text(),'20210225000003')]"));
		}
		public static WebElement Doc_nav(WebDriver driver) {
			return driver.findElement(By.xpath("//*[contains(text(),'Documents')]"));
		}
		public static WebElement Doc_1(WebDriver driver) {
			return driver.findElement(By.xpath("//*[@id=\"document\"]/div[1]/div[2]/div/div/div[2]/a"));
		}
		public static WebElement Doc_2(WebDriver driver) {
			return driver.findElement(By.xpath("//*[@id=\"document\"]/div[1]/div[3]/div/div/div[2]/a"));
		}
		public static WebElement Doc_3(WebDriver driver) {
			return driver.findElement(By.xpath("//*[@id=\"document\"]/div[1]/div[4]/div/div/div[2]/a"));
		}
		public static WebElement Doc_4(WebDriver driver) {
			return driver.findElement(By.xpath("//*[@id=\"document\"]/div[1]/div[5]/div/div/div[2]/a"));
		}
		public static WebElement Doc_5(WebDriver driver) {
			return driver.findElement(By.xpath("//*[@id=\"document\"]/div[1]/div[6]/div/div/div[2]/a"));
		}
		public static WebElement Doc_6(WebDriver driver) {
			return driver.findElement(By.xpath("//*[@id=\"document\"]/div[1]/div[7]/div/div/div[2]/a"));
		}
		public static WebElement Doc_7(WebDriver driver) {
			return driver.findElement(By.xpath("//*[@id=\"document\"]/div[1]/div[8]/div/div/div[2]/a"));
		}

		
//Query & Bills
		
		public static WebElement querylink(WebDriver driver) {
			return driver.findElement(By.partialLinkText("Query & Interim Bil"));
		}
		public static WebElement Interim(WebDriver driver) {
			return driver.findElement(By.xpath("//*[@id=\"interimBillCheckBox\"]"));
		}
		public static List<WebElement> num_rows(WebDriver driver) {
			return driver.findElements(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr"));
		}
		public static WebElement img_capture(WebDriver driver) {
			return driver.findElement(By.xpath("//*[contains(text(),'Capture Image')]"));
		}
		public static WebElement ok_img(WebDriver driver) {
			return driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
		}
		public static WebElement Enhancement_amount(WebDriver driver) {
			return driver.findElement(By.xpath("//input[contains(@name,'enhancementAmount')]"));
		}
		public static WebElement Interim_doc1(WebDriver driver) {
			return driver.findElement(By.xpath("(//*[@id=\"drop-area\"])[2]"));
		}
		public static WebElement Interim_doc2(WebDriver driver) {
			return driver.findElement(By.xpath("(//*[@id=\"drop-area\"])[3]"));
		}
		public static WebElement Interim_doc3(WebDriver driver) {
			return driver.findElement(By.xpath("(//*[@id=\"drop-area\"])[4]"));
		}
		public static WebElement Interim_doc4(WebDriver driver) {
			return driver.findElement(By.xpath("(//*[@id=\"drop-area\"])[5]"));
		}
		public static WebElement Interim_img1(WebDriver driver) {
			return driver.findElement(By.xpath("//div[@name=\"image00\"]"));
		}
		public static WebElement Interim_img2(WebDriver driver) {
			return driver.findElement(By.xpath("//div[@name=\"image01\"]"));
		}
		public static WebElement Interim_img3(WebDriver driver) {
			return driver.findElement(By.xpath("//div[@name=\"image11\"]"));
		}
		public static WebElement Interim_img4(WebDriver driver) {
			return driver.findElement(By.xpath("//div[@name=\"image21\"]"));
		}
		public static WebElement Interim_upload1(WebDriver driver) {
			return driver.findElement(By.xpath("(//*[contains(text(),'Upload')])[2]"));
		}
		public static WebElement Interim_upload2(WebDriver driver) {
			return driver.findElement(By.xpath("(//*[contains(text(),'Upload')])[4]"));
		}
		public static WebElement Interim_upload3(WebDriver driver) {
			return driver.findElement(By.xpath("(//*[contains(text(),'Upload')])[6]"));
		}
		
		public static WebElement Interim_upload4(WebDriver driver) {
			return driver.findElement(By.xpath("(//*[contains(text(),'Upload')])[8]"));
		}
		public static WebElement doc_typ1(WebDriver driver) {
			return driver.findElement(By.xpath("//select[@name=\"documentType101\"]"));
		}
		public static WebElement doc_typ2(WebDriver driver) {
			return driver.findElement(By.xpath("//select[@name=\"documentType111\"]"));
		}
		public static WebElement doc_typ3(WebDriver driver) {
			return driver.findElement(By.xpath("//select[@name=\"documentType121\"]"));
		}
		
		public static WebElement query_upload(WebDriver driver) {
			return driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[1]/td[4]/form/span/span/div/button"));
		}
		
		
// Final Bill
		public static WebElement final_bill(WebDriver driver) {
		return driver.findElement(By.linkText("Final Bills"));
	}
		public static WebElement total_bill(WebDriver driver) {
			return driver.findElement(By.xpath("//input[contains(@name,'amount')]"));
		}
	//doc1	
		public static WebElement first_doc_DD1(WebDriver driver) {
			return driver.findElement(By.xpath("//select[contains(@name,'documentType100')]"));
		}
		public static WebElement first_doc1(WebDriver driver) {
			return driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area'])[1]"));
		}
		public static WebElement first_doc1_upload(WebDriver driver) {
			return driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]"));
		}
		public static WebElement first_doc_DD2(WebDriver driver) {
			return driver.findElement(By.xpath("//select[contains(@name,'documentType110')]"));
		}
		public static WebElement first_doc2(WebDriver driver) {
			return driver.findElement(By.xpath("(//div/select[@name='documentType110']/following::div/div[@id='drop-area'])[1]"));
		}
		public static WebElement first_doc2_upload(WebDriver driver) {
			return driver.findElement(By.xpath("(//div/select[@name='documentType110']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]"));
		}
		public static WebElement first_doc_DD3(WebDriver driver) {
			return driver.findElement(By.xpath("//select[contains(@name,'documentType120')]"));
		}
		public static WebElement first_doc3(WebDriver driver) {
			return driver.findElement(By.xpath("(//div/select[@name='documentType120']/following::div/div[@id='drop-area'])[1]"));
		}
		public static WebElement first_doc3_upload(WebDriver driver) {
			return driver.findElement(By.xpath("(//div/select[@name='documentType120']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]"));
		}
		public static WebElement first_doc_DD4(WebDriver driver) {
			return driver.findElement(By.xpath("//select[contains(@name,'documentType130')]"));
		}
		public static WebElement first_doc4(WebDriver driver) {
			return driver.findElement(By.xpath("(//div/select[@name='documentType130']/following::div/div[@id='drop-area'])[1]"));
		}
		public static WebElement first_doc4_upload(WebDriver driver) {
			return driver.findElement(By.xpath("(//div/select[@name='documentType130']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]"));
		}
		//doc2
		public static WebElement sec_doc_DD1(WebDriver driver) {
			return driver.findElement(By.xpath("//select[contains(@name,'documentType101')]"));
		}
		public static WebElement sec_doc1(WebDriver driver) {
			return driver.findElement(By.xpath("(//div/select[@name='documentType101']/following::div/div[@id='drop-area'])[1]"));
		}
		public static WebElement sec_doc1_upload(WebDriver driver) {
			return driver.findElement(By.xpath("(//div/select[@name='documentType101']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]"));
		}
		//doc3
				public static WebElement D3_doc_DD1(WebDriver driver) {
					return driver.findElement(By.xpath("//select[contains(@name,'documentType102')]"));
				}
				public static WebElement D3_doc1(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType102']/following::div/div[@id='drop-area'])[1]"));
				}
				public static WebElement D3_doc1_upload(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType102']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]"));
				}
				public static WebElement D3_doc_DD2(WebDriver driver) {
					return driver.findElement(By.xpath("//select[contains(@name,'documentType112')]"));
				}
				public static WebElement D3_doc2(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType112']/following::div/div[@id='drop-area'])[1]"));
				}
				public static WebElement D3_doc2_upload(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType112']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]"));
				}
				public static WebElement D3_doc_DD3(WebDriver driver) {
					return driver.findElement(By.xpath("//select[contains(@name,'documentType122')]"));
				}
				public static WebElement D3_doc3(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType122']/following::div/div[@id='drop-area'])[1]"));
				}
				public static WebElement D3_doc3_upload(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType122']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]"));
				}
				public static WebElement D3_doc_DD4(WebDriver driver) {
					return driver.findElement(By.xpath("//select[contains(@name,'documentType132')]"));
				}
				public static WebElement D3_doc4(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType132']/following::div/div[@id='drop-area'])[1]"));
				}
				public static WebElement D3_doc4_upload(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType132']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]"));
				}
				public static WebElement D3_doc_DD5(WebDriver driver) {
					return driver.findElement(By.xpath("//select[contains(@name,'documentType142')]"));
				}
				public static WebElement D3_doc5(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType142']/following::div/div[@id='drop-area'])[1]"));
				}
				public static WebElement D3_doc5_upload(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType142']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]"));
				}
				
				public static WebElement D3_doc_DD6(WebDriver driver) {
					return driver.findElement(By.xpath("//select[contains(@name,'documentType152')]"));
				}
				public static WebElement D3_doc6(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType152']/following::div/div[@id='drop-area'])[1]"));
				}
				public static WebElement D3_doc6_upload(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType152']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]"));
				}
				public static WebElement D3_doc_DD7(WebDriver driver) {
					return driver.findElement(By.xpath("//select[contains(@name,'documentType162')]"));
				}
				public static WebElement D3_doc7(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType162']/following::div/div[@id='drop-area'])[1]"));
				}
				public static WebElement D3_doc7_upload(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType162']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]"));
				}
				public static WebElement D3_doc_DD8(WebDriver driver) {
					return driver.findElement(By.xpath("//select[contains(@name,'documentType172')]"));
				}
				public static WebElement D3_doc8(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType172']/following::div/div[@id='drop-area'])[1]"));
				}
				public static WebElement D3_doc8_upload(WebDriver driver) {
					return driver.findElement(By.xpath("(//div/select[@name='documentType172']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]"));
				}
					
				public static List<WebElement> cmn_dwnld(WebDriver driver) {
					return driver.findElements(By.xpath("//a[contains(@class,'fa fa-download')]"));
				}}
