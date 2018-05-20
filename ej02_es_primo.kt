
fun main(args: Array<String>){

	if(args.size == 0){
		println("No hay parametros de entrada")
	}else{
		var numero: Int? = args[0].toIntOrNull()
		
		if(numero == null){
			println("La entrada '" + args[0] + "' tiene que ser un numero")
			return
		}
		
		var anterior = (numero - 1)
		
		for (i in 2..anterior){
			if(numero % i ==0){
				println("EL numero " + numero +  " no es primo")
				return
			}
			
		}
		println("El numero "+ numero + " es primo")
	}

}