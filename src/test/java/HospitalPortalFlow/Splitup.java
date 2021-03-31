package HospitalPortalFlow;

public class Splitup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String DD1 = "";
		String DD2 = "";
		String DD3 = "";
		String DD4 = "";
		try {
		String input = "Karthee,Vicky,Sakkarai_Deivanai,b,c";

		if (input !=null && input !="") {
			String[] inputArray = input.split(",");
			if (inputArray != null && inputArray.length > 0) {
				for (int i = 0; i < inputArray.length; i++) {
					switch (i) {
					case 0:
						DD1 = inputArray[i];
						break;
					case 1:
						DD2 = inputArray[i];
						break;
					case 2:
						DD3 = inputArray[i];
						break;
					case 3:
						DD4 = inputArray[i];
						break;
					}
				}
			}
		}

		System.out.println("first " + DD1);
		System.out.println("two " + DD2);
		System.out.println("three " + DD3);
		System.out.println("four " + DD4);
		}
		 catch (Exception e) {
				
				e.printStackTrace();
			}
	}
	

}
