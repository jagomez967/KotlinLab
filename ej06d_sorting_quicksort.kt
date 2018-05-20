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
	
	var numerosOrdenados = quickSort(numeros)
	
	for( num in numerosOrdenados){
		print(" " + num )
	}
	print("\n")

}

fun quickSort(numeros: IntArray) : IntArray{

	if(numeros.size == 2){
		if(numeros[0] < numeros[1] ){
			return intArrayOf(numeros[0], numeros[1])
		}else{
			return intArrayOf(numeros[1], numeros[0])
		}
	}else if(numeros.size == 1){
		return numeros
	}


	// Identificar un numero y su posicion, para usar de pivote
	var posicion = numeros.size / 2
	
	var pivote = numeros[  posicion  ]
	while( esElMayor(numeros, pivote) || esElMenor(numeros, pivote)){
		pivote = numeros[  ((++posicion) % numeros.size)  ]
	}
	
	// Definimos los tamaños de los sub arrays
	var menoresAPivote :Int = 0
	for(i in 0..(numeros.size - 1) ){
		if(numeros[i] <= pivote){
			menoresAPivote++
		}
	}
	var numerosBajos  = IntArray(menoresAPivote)
	var numerosAltos  = IntArray(numeros.size - numerosBajos.size)
	
	// Cargar los sub arrays
	var iBajos = 0
	var iAltos = 0
	for(i in 0..(numeros.size - 1) ){
		if(numeros[i] <= pivote){
			numerosBajos[ iBajos++ ] = numeros[i]
		}else{
			numerosAltos[ iAltos ] = numeros[i]
			iAltos = iAltos + 1
		}
	}
	
	var numerosBajosOrdenados = quickSort(numerosBajos)
	var numerosAltosOrdenados = quickSort(numerosAltos)
	
	var numerosOrdenados = IntArray(numeros.size)
	
	for(i in 0..(numerosBajosOrdenados.size - 1) ){
		numerosOrdenados[i] = numerosBajosOrdenados[i]
	}
	
	for(i in 0..(numerosAltosOrdenados.size - 1) ){
		numerosOrdenados[numerosBajosOrdenados.size + i] = numerosAltosOrdenados[i]
	}
	
	return numerosOrdenados
}

fun esElMayor(numeros : IntArray, pivote: Int) : Boolean{
	for(i in numeros){
		if(i > pivote){
			return false
		}
	}
	return true
}

fun esElMenor(numeros : IntArray, pivote: Int) : Boolean{
	for(i in numeros){
		if(i < pivote){
			return false
		}
	}
	return true
}

