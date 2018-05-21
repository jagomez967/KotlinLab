
fun decimeCuantosBilletesDe(vuelto: Int, denominacion: Int) : Int{
	if (vuelto >=denominacion){
		println("Billetes de " + denominacion + " x " + (vuelto / denominacion) )
	}
	return vuelto % denominacion;
}

fun main(args: Array<String>){
	if(args.size == 0){
		println("No hay parametros de entrada")
		return
	}

	var numero: Int? = args[0].toIntOrNull()

	if(numero == null){
		println("La entrada '" + args[0] + "' tiene que ser un numero")
		return
	}
	val listaBilletes: IntArray = intArrayOf(100, 50, 20, 10, 5, 2 , 1)

	for(billete in listaBilletes)
		if(numero != null)
			numero = decimeCuantosBilletesDe(numero,billete)
}
