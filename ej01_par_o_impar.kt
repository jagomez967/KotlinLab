
fun main(args: Array<String>){

	if(args.size == 0){
		println("No hay parametros de entrada")
	}else{
		var numero: Int? = args[0].toIntOrNull()
		
		if(numero == null){
			println("La entrada '" + args[0] + "' tiene que ser un numero")
			return
		}
		
		var resto = numero % 2;
		
		if( resto == 0){
			println("El numero '" + numero + "' es PAR")
		}else{ 
			println("El numero '" + numero + "' es IMPAR")
		}
		
		
	}

}