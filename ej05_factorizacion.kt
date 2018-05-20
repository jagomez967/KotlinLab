
fun esPrimo(num: Int): Boolean{
    for( i in 2..(num-1)){
        if( num % i == 0){
            return false
        }
    }
	return true    
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
		
        var acum: Int? = numero
        for(i in 2..(numero - 1)){
			if(esPrimo(i)){
                while(acum % i == 0){
                    println(i)
                    acum = acum / i
                }
            }
        }
	}
}