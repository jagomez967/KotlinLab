

fun main(args: Array<String>){

	if(args.size == 0){
		println("No hay parametros de entrada")
	}else{
		var tope: Int? = args[0].toIntOrNull()

		if(tope == null){
			tope = 10
		}

		println("Voy a contar hasta " + tope + "...")

		for( i in 0..tope){
			println(i)
		}


	}
//ningun cambio en especial
}
