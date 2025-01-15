// Variables globales
let bestScore = Infinity; // Infinito positivo
let mejorRuta = shuffleArray(Array.from({ length: 11 }, (_, i) => i + 1));

const listaComunidades = ["","Ciudad de México","Aguascalientes","Baja California","Baja California Sur","Campeche","Chiapas","Chihuahua","Coahuila","Colima","Durango"]

const coordenadasComunidades = [
    { lat: "", lng: "" }, // Punto vacío
    {lat: "19.50955637", lng: "-99.15457376"},  //Ciudad de México
    {lat: "21.884719", lng: "-102.298988"}, //AGUASCALIENTES
    {lat: "32.437727", lng: "-116.97538"},  //BAJA CALIFORNIA
    {lat: "24.14437", lng: "-110.3005"},    //BAJA CALIFORNIA SUR
    {lat: "19.8439", lng: "-90.5255"},  //CAMPECHE
    {lat: "16.7529", lng: "-93.1162"},  //CHIAPAS
    {lat: "28.6333", lng: "-106.0711"}, //CHIHUAHUA
    {lat: "25.4383", lng: "-100.9777"}, //COAHUILA
    {lat: "19.2855", lng: "-103.7632"}, //COLIMA
    {lat: "24.0222", lng: "-104.6576"}  //DURANGO
];


const M = [
    [0,0,0,0,0,0,0,0,0,0,0],
    [0,0,420,2282,1261,905,708,1231,683,485,757],
    [0,420,0,1863,857,1246,1119,838,416,326,338],
    [0,2282,1863,0,1128,2978,2964,1127,1739,1965,1525],
    [0,1261,857,1128,0,2095,1969,652,953,864,574],
    [0,905,1246,2978,2095,0,438,1852,1239,1390,1531],
    [0,708,1119,2964,1969,438,0,1869,1261,1162,1448],
    [0,1231,838,1127,652,1852,1869,0,617,1062,530],
    [0,683,416,1739,953,1239,1261,617,0,739,404],
    [0,485,326,1965,864,1390,1162,1062,739,0,533],
    [0,757,338,1525,574,1531,1448,530,404,533,0]
];

let elitismo = 0;

// Función para generar un array aleatorio
function shuffleArray(array) {
    // Guardamos el primer elemento
    const firstElement = array[0];
    
    // Obtenemos un subarreglo con el resto de elementos
    const restOfArray = array.slice(1);
    
    // Mezclamos el subarreglo
    const shuffledRest = restOfArray.sort(() => Math.random() - 0.5);
    
    // Retornamos un nuevo arreglo combinando el primer elemento con el resto mezclado
    return [firstElement, ...shuffledRest,firstElement];
}

// Generar cromosomas (recorrido inicial)
function crearCromosomas() {
    return shuffleArray(Array.from({ length: 10 }, (_, i) => i + 1));
}

// Evaluar el fitness de un cromosoma
function evaluarCromosoma(cromosoma) {
    const F1 = [];
    for (let i = 0; i < cromosoma.length - 1; i++) {
        F1.push(M[cromosoma[i]][cromosoma[i + 1]]);
    }
    // Agregar la distancia de regreso a la ciudad inicial
    //F1.push(M[cromosoma[cromosoma.length - 1]][cromosoma[0]]);

    // Ajustar elitismo
    elitismo = F1[0] <= 574 && F1[1] <= 574 ? 3 : F1[0] <= 574 ? 2 : 1;

    return [F1, F1.reduce((a, b) => a + b, 0)];
}

// Modificar un cromosoma
function modificarCromosoma(cromosoma, F) {
    const c = [...cromosoma];
    let j = 0;

    for (let i = elitismo; i < F.length - 1; i++) {
        if (F[i] <= 574) {
            const a = c.splice(i, 1)[0];
            c.splice(elitismo, 0, a);
            if (j < 4) j += 1;
        }
    }

    const randomIndex = Math.floor(Math.random() * (c.length - (elitismo + j)-1) + (elitismo + j));
    const genoma = c.splice(randomIndex, 1)[0];
    c.splice(Math.floor(Math.random() * (c.length - (elitismo + j)-1) + (elitismo + j)), 0, genoma);

    return c;
}

// Actualizar a la siguiente generación
function nextGeneration(cromosomas, F0) {
    const nuevosCromosomas = modificarCromosoma(cromosomas, F0);
    const [nuevoF0, nuevoFitness] = evaluarCromosoma(nuevosCromosomas);

    if (nuevoFitness < bestScore) {
        bestScore = nuevoFitness;
        mejorRuta = nuevosCromosomas;
    }

    return [nuevosCromosomas, nuevoF0];
}

// Obtener la mejor ruta
function obtenerMejorRuta() {
    // Generar cromosomas iniciales
    let cromosomas = crearCromosomas();
    console.log("Recorrido inicial:", cromosomas);
    
    // Evaluar el cromosoma inicial
    let [F0, distanciaInicial] = evaluarCromosoma(cromosomas);    
    console.log("Fitness inicial:", F0);
    console.log("Distancia inicial:", distanciaInicial);

    bestScore = distanciaInicial;
    mejorRuta = cromosomas;
    
    generaciones=0
    // Iteraciones del algoritmo genético
    while(bestScore>7200 && generaciones<100000){
        [cromosomas, F0] = nextGeneration(cromosomas, F0);
        generaciones+=1
    }

    console.log("Generaciones:", generaciones);
    return [mejorRuta, bestScore];
}

function trazarRuta()
{
    // Ejecución del algoritmo
    const [rutaGIS, distancia] = obtenerMejorRuta();
    console.log("Mejor ruta:", rutaGIS, "Distancia total:", distancia);

    // Crear una nueva lista reordenada
    const listaReordenada = rutaGIS.map(index => listaComunidades[index]);

    //Ruta de coordenadas
    const ruta=rutaGIS.map(index => coordenadasComunidades[index])

    return [listaReordenada,ruta,distancia]
}
