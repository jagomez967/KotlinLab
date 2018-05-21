fun _fibonacci(anterior: Int,actual: Int,corte: Int){
	var nuevo: Int = anterior + actual
	println(actual)
	if(corte > nuevo)
		return _fibonacci(actual,nuevo,corte)
}

fun fibonacci(maximo: Int){
	 _fibonacci(0,1,maximo)
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
	fibonacci(numero)
}
