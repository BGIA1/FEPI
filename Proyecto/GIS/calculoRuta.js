// Variables globales
let bestScore = Infinity; // Infinito positivo
let mejorRuta = shuffleArray(Array.from({ length: 8 }, (_, i) => i + 1));

const coordenadasComunidades = [
    { lat: "", lng: "" }, // Punto vacío
    { lat: "19.50955637191590", lng: "-99.15457376402300" }, // CDMX
    { lat: "18.514357211994800", lng: "-88.32729808936540" }, // QUINTANA ROO
    { lat: "22.12178582641720", lng: "-100.98269946108200" }, // SAN LUIS POTOSÍ
    { lat: "19.078832995653300", lng: "-98.20772925929980" }, // PUEBLA
    { lat: "19.50769992378020", lng: "-96.88696693180490" }, // VERACRUZ
    { lat: "21.028226770454000", lng: "-89.51790719122570" }, // YUCATÁN
    { lat: "22.730429511923000", lng: "-102.52729066490800" } // ZACATECAS
];


const M = [
    [0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 1339, 395, 135, 318, 1332, 579],
    [0, 1339, 0, 1819, 1200, 1043, 265, 1948],
    [0, 395, 1819, 0, 515, 771, 1727, 185],
    [0, 135, 1200, 515, 0, 291, 1204, 712],
    [0, 318, 1043, 771, 291, 0, 1028, 977],
    [0, 1332, 265, 1727, 1204, 1047, 0, 1933],
    [0, 579, 1948, 185, 712, 977, 1933, 0]
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
    return shuffleArray(Array.from({ length: 7 }, (_, i) => i + 1));
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
    elitismo = F1[0] <= 395 && F1[1] <= 395 ? 3 : F1[0] <= 395 ? 2 : 1;

    return [F1, F1.reduce((a, b) => a + b, 0)];
}

// Modificar un cromosoma
function modificarCromosoma(cromosoma, F) {
    const c = [...cromosoma];
    let j = 0;

    for (let i = elitismo; i < F.length - 1; i++) {
        if (F[i] <= 395) {
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
    
    // Iteraciones del algoritmo genético
    for (let generacion = 0; generacion < 300; generacion++) {
        [cromosomas, F0] = nextGeneration(cromosomas, F0);
    }

    return [mejorRuta, bestScore];
}

// Ejecución del algoritmo
const [rutaGIS, distancia] = obtenerMejorRuta();
console.log("Mejor ruta:", rutaGIS, "Distancia:", distancia);
