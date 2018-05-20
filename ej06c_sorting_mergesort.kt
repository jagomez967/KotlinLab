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
	
	var numerosOrdenados = mergeSort(numeros)
	
	imprimirFuncion(numerosOrdenados)

}

fun mergeSort(numeros: IntArray) : IntArray{

	if(numeros.size == 2){
		if(numeros[0] < numeros[1] ){
			return intArrayOf(numeros[0], numeros[1])
		}else{
			return intArrayOf(numeros[1], numeros[0])
		}
	}else if(numeros.size == 1){
		return numeros
	}
	
	var primeraMitad = IntArray(numeros.size / 2)
	var segundaMitad = IntArray(numeros.size - primeraMitad.size)
	
	for( i in 0.. (numeros.size -1) ){
		if(primeraMitad.size > i){
			primeraMitad[i] = numeros[i]
		}else{
			segundaMitad[i - primeraMitad.size] = numeros[i]
		}
	}
	
	var primeraMitadOrdenada = mergeSort(primeraMitad)
	var segundaMitadOrdenada = mergeSort(segundaMitad)
	
	var numerosOrdenados = IntArray(numeros.size)
	
	var indice1 = 0
	var indice2 = 0
	var indiceFinal = 0
	
	while(indice1 < primeraMitadOrdenada.size || indice2 < segundaMitadOrdenada.size){
	
		var terminoElPrimero = indice1 >= primeraMitadOrdenada.size
		var terminoElSegundo = indice2 >= segundaMitadOrdenada.size
	
		if( !terminoElPrimero && ( terminoElSegundo || primeraMitadOrdenada[indice1] <= segundaMitadOrdenada[indice2])){
			numerosOrdenados[indiceFinal] = primeraMitadOrdenada[indice1]
			indiceFinal++
			indice1++
		}else if( terminoElPrimero || primeraMitadOrdenada[indice1] > segundaMitadOrdenada[indice2]){
			numerosOrdenados[indiceFinal] = segundaMitadOrdenada[indice2]
			indiceFinal++
			indice2++
		}
	
	}
	
	return numerosOrdenados
}

fun imprimirFuncion(numeros : IntArray){
for( num in numeros){
		print(" " + num )
	}
	print("\n")
}
