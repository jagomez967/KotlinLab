fun main(args: Array<String>){

	var numeros = IntArray(args.size)

	for( i in 0..(args.size -1)){
		var num = args[i].toIntOrNull()
		if(num == null){
			println("Numero invalido: " + num)
			return
		}
		numeros[i]=num
	}
	for( j in 2..(numeros.size - 1 ) ){
		for( i in 0..(numeros.size - j) ){
			var num1 = numeros[i]
			var num2 = numeros[i + 1]
			if(num1 > num2){
				numeros[i] = num2
				numeros[i+1] = num1 
			}
		}
	}
	
	
	for( num in numeros){
		print(" " + num )
	}

}