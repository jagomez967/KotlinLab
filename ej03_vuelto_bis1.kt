
fun decimeCuantosBilletesDe(vuelto: Int, denominacion: Int) : Int{
	if (vuelto >=denominacion){ 
		println("Billetes de " + denominacion + " x " + (vuelto / denominacion) )
	}
	
	return vuelto % denominacion;
}

fun main(args: Array<String>){

	if(args.size == 0){
		println("No hay parametros de entrada")
	}else{
		var numero: Int? = args[0].toIntOrNull()
		
		if(numero == null){
			println("La entrada '" + args[0] + "' tiene que ser un numero")
			return
		}
		
		
		
		numero = decimeCuantosBilletesDe(numero, 100)
		numero = decimeCuantosBilletesDe(numero, 50)
		numero = decimeCuantosBilletesDe(numero, 20)
		numero = decimeCuantosBilletesDe(numero, 10)
		numero = decimeCuantosBilletesDe(numero, 5)
		numero = decimeCuantosBilletesDe(numero, 2)
		decimeCuantosBilletesDe(numero, 1)

		
		
	}

}