using System;

namespace _08_Maksukortti
{
	public class Program
	{
		public static void Main(String[] args)
		{
			Maksukortti pekanKortti = new Maksukortti(20);
			Maksukortti matinKortti = new Maksukortti(30);
			pekanKortti.SyoMaukkaasti();
			matinKortti.SyoEdullisesti();
			Console.WriteLine("Pekka: " + pekanKortti);
			Console.WriteLine("Matti: " + matinKortti);
			pekanKortti.LataaRahaa(20);
			matinKortti.SyoMaukkaasti();
			Console.WriteLine("Pekka: " + pekanKortti);
			Console.WriteLine("Matti: " + matinKortti);
			pekanKortti.SyoEdullisesti();
			pekanKortti.SyoEdullisesti();
			matinKortti.LataaRahaa(50);
			Console.WriteLine("Pekka: " + pekanKortti);
			Console.WriteLine("Matti: " + matinKortti);
		}
	}
}
