
public class StringUtil

{
	// Retorna una cadena compuesta por n caracteres c
	// Ejemplo: replicate('x',5) ==> 'xxxxx'
	public static String replicate(char c, int n)
	{
		String str = Character.toString(c);
		String replication = str.repeat(n);
		System.out.print(replication);
		return replication;
	}

	// Retorna una cadena de longitud n, compuesta por s
	// y precedida de tantos caracteres c como sea necesario
	// para completar la longitud mencionada
	// Ejemplo lpad("5",3,'0') ==> "005"
	public static String lpad(String s, int n, char c) {

		String chain = "";
		if(s.length()< n) {
			int total = n-s.length();
			for (int i = 0; i < total; i++) {
				chain = chain + c;
			}


			s = chain + s;

			System.out.println(s);
		}
		return s;
	}

	// Retorna un String[] conteniendo los elementos de arr
	// representados como cadenas de caracteres
	public static String[] toStringArray(int arr[])
	{
		int size = arr.length;
		String[] rta = new String[size];
		for(int i = 0; i<arr.length;i++){
			rta[i]= "\""+arr[i] + "\"";
		}
		return rta;
	}

	// Retorna un String[] conteniendo los elementos de arr
	// representados como cadenas de caracteres
	public static int[] toIntArray(String arr[])
	{
		int[] num = toIntArray(arr);
		return num;
	}

	// Retorna la longitud del elemento con mayor cantidad
	// de caracteres del array arr
	public static int maxLength(String arr[])
	{
		int counter = arr[0].length();
		for (int i = 0;i<arr.length;i++){
			if(arr[i].length() > counter) {
				counter = arr[i].length();
			}
		}

		return counter;
	}

	// Completa los elemento del arr agregando caracteres c
	// a la izquierda, dejando a todos con la longitud del mayor
	public static String[] lNormalize(String arr[], char c)

	{
		int counter = arr[0].length();
		int value = 0;
		String chain = "";
		for (int i = 0;i<arr.length;i++){
			chain = "";
			if(arr[i].length() > counter) {
				counter = arr[i].length();
			}
			else
				value = arr[i].length();
				int total = counter - value;
				for (int j = 0; j<total; j++){
					chain = chain + c;

				}
				arr[i] = arr[i].replace("\"","");
				arr[i] = "\"" +chain + arr[i] + "\"";
		}
		return arr;
	}
}
