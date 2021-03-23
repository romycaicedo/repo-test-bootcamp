import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;

public class RadixSortEjerc
{
	public static void radixSort(int []arr)
	{
		int n = arr.length;
		String sArr[] = StringUtil.toStringArray(arr);
		String sArrNorm[] = StringUtil.lNormalize(sArr,'0');
		for(int x= 0; x < sArrNorm.length; x++){
			sArrNorm[x] = sArrNorm[x].replace("\"","");
		}
		int m = StringUtil.maxLength(sArrNorm);
		for (int exp = 1; m / exp > 0; exp *= 10){
			countSort(sArrNorm, m, exp);
		}
		//print(sArrNorm, n);

	}

	public static void countSort(String []sArrNorm, int m, int exp){
		int counter = 1;
		while(counter <= m) {
			List<String> general = new ArrayList<>();
			List<String> L0 = new ArrayList<>();
			List<String> L1 = new ArrayList<>();
			List<String> L2 = new ArrayList<>();
			List<String> L3 = new ArrayList<>();
			List<String> L4 = new ArrayList<>();
			List<String> L5 = new ArrayList<>();
			List<String> L6 = new ArrayList<>();
			List<String> L7 = new ArrayList<>();
			List<String> L8 = new ArrayList<>();
			List<String> L9 = new ArrayList<>();
			int max = StringUtil.maxLength(sArrNorm);
			int posicion = 1;
			if(counter >= 1)
				posicion = counter;
			for (int i = 0; i < sArrNorm.length; i++) {

				switch (sArrNorm[i].charAt(sArrNorm[i].length() - posicion)) {
					case '0':
						L0.add(sArrNorm[i]);
						sArrNorm[i] = null;
						break;
					case '1':
						L1.add(sArrNorm[i]);
						sArrNorm[i] = null;
						break;
					case '2':
						L2.add(sArrNorm[i]);
						sArrNorm[i] = null;
						break;
					case '3':
						L3.add(sArrNorm[i]);
						sArrNorm[i] = null;
						L3.toArray();
						break;
					case '4':
						L4.add(sArrNorm[i]);
						sArrNorm[i] = null;
						break;
					case '5':
						L5.add(sArrNorm[i]);
						sArrNorm[i] = null;
						break;
					case '6':
						L6.add(sArrNorm[i]);
						sArrNorm[i] = null;
						break;
					case '7':
						L7.add(sArrNorm[i]);
						sArrNorm[i] = null;
						break;
					case '8':
						L8.add(sArrNorm[i]);
						sArrNorm[i] = null;
						L8.toArray();
						break;
					case '9':
						L9.add(sArrNorm[i]);
						sArrNorm[i] = null;
						L9.toArray();
						break;

				}


			}
			general.addAll(L0);
			general.addAll(L1);
			general.addAll(L2);
			general.addAll(L3);
			general.addAll(L4);
			general.addAll(L5);
			general.addAll(L6);
			general.addAll(L7);
			general.addAll(L8);
			general.addAll(L9);

			for (int g = 0; g < sArrNorm.length; g++){
				sArrNorm[g] = general.get(g);
			}




			for (int r = 0; r < sArrNorm.length; r++) {
				System.out.println(sArrNorm[r]);
			}
			counter++;


		}
	}



	static void print(String arr[], int n)
	{
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
	}

	public static void main(String[] args)
	{



		int arr[]={16223,898,13,906,235,23,9,1532,6388,2511,8};
		int n = arr.length;

		radixSort(arr);




		/*for(int i=0; i<arr.length;i++)
		{
			System.out.print(arr[i]+(i<arr.length-1?",":""));
		}*/
	}
}

