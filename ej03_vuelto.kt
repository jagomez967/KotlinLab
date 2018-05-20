
fun main(args: Array<String>){

	if(args.size == 0){
		println("No hay parametros de entrada")
	}else{
		var numero: Int? = args[0].toIntOrNull()
		
		if(numero == null){
			println("La entrada '" + args[0] + "' tiene que ser un numero")
			return
		}
		
		if (numero >=100){ 
			println("Billetes de 100 x " + (numero / 100) )
		}
		numero = numero % 100
		
		if (numero >=50){ 
			println("Billetes de 50 x " + (numero / 50) )
		}
		numero = numero % 50
		
		if (numero >=20){ 
			println("Billetes de 20 x " + (numero / 20) )
		}
		numero = numero % 20
		if (numero >=10){ 
			println("Billetes de 10 x " + (numero / 10) )
		}
		numero = numero % 10
		if (numero >=5){ 
			println("Billetes de 5 x " + (numero / 5) )
		}
		numero = numero % 5
		
		if (numero >=2){ 
			println("Billetes de 2 x " + (numero / 2) )
		}
		numero = numero % 2
		
		if (numero >=1){ 
			println("Billetes de 1 x " + (numero ) )
		}
		
		
	}

}