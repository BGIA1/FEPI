
// Variables globales
let bestScore = Infinity; // Infinito positivo
let bestFitness= []
let mejorRuta = shuffleArray(Array.from({ length: 28 }, (_, i) => i + 1));
let mArticulos=11000


let lstArticulosEdos=[]

const coordenadasComunidades = [
    { lat: "", lng: "" }, // Punto vacío
    {lat: "19.50955637", lng: "-99.15457376"},  //Ciudad de México
    {lat: "21.884719", lng: "-102.298988"}, //AGUASCALIENTES
    {lat: "32.437727", lng: "-116.97538"},  //BAJA CALIFORNIA
    {lat: "24.1351340377", lng: "-110.3440550"},    //BAJA CALIFORNIA SUR
    {lat: "19.8439", lng: "-90.5255"},  //CAMPECHE
    {lat: "16.7529", lng: "-93.1162"},  //CHIAPAS
    {lat: "28.6333", lng: "-106.0711"}, //CHIHUAHUA
    {lat: "25.4383", lng: "-100.9777"}, //COAHUILA
    {lat: "19.2855", lng: "-103.7632"}, //COLIMA
    {lat: "24.0222", lng: "-104.6576"},  //DURANGO
    {lat: "20.974806082868", lng:"-101.314592909384"}, //GUANAJUATO
    {lat: "17.535042400443", lng:"-99.4990972108770"}, //GUERRERO
    {lat: "20.12147446927", lng:"-98.7131141730040"}, //HIDALGO
    {lat: "20.6903373779262", lng:"-103.37224978398"}, //JALISCO
    {lat: "19.2963288717898", lng:"-99.6721150038441"}, //ESTADO DE MÉXICO
    {lat: "19.7186715852428", lng:"-101.1525399296610"}, //MICHOACÁN
    {lat: "18.9489694534729", lng:"-99.2302486632073"}, //MORELOS
    {lat: "21.4869596342554", lng:"-104.8793787890670"}, //NAYARIT
    {lat: "25.6585911204592", lng:"-100.226489944034"}, //NUEVO LEÓN
    {lat: "17.0565120240103", lng:"-96.6733258034319"}, //OAXACA
    {lat: "19.0788329956533", lng:"-98.2077292592998"}, //PUEBLA
    {lat: "20.550741248206", lng:"-100.42900359589"}, //QUERÉTARO
    {lat: "18.5143572119948", lng:"-88.3272980893654"}, //QUINTANA ROO
    {lat: "22.121785826417", lng:"-100.9826994610820"}, //SAN LUIS POTOSÍ
    {lat: "19.50769992378", lng:"-96.88696693180"},     //VERACRUZ
    {lat: "21.01404001460", lng:"-89.5896787762"},      //YUCATÁN
    {lat: "22.73101899008", lng:"-102.5228066977"}      //ZACATECAS
];

const M = [
[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
[0,0,420,2282,1261,905,708,1231,683,485,757,276,220,81,457,59,211,63,636,690,378,110,176,1145,346,238,1020,500],
[0,420,0,1863,857,1246,1119,838,416,326,338,144,562,419,174,398,268,456,271,468,796,528,244,1506,138,622,1328,97],
[0,2282,1863,0,1128,2978,2964,1127,1739,1965,1525,2007,2408,2270,1880,2260,2115,2319,1705,1793,2659,2388,2106,3252,1949,2462,2998,1784],
[0,1261,857,1128,0,2095,1969,652,953,864,574,992,1339,1273,813,1224,1064,1282,629,1031,1621,1371,1092,1683,980,1478,2163,810],
[0,905,1246,2978,2095,0,438,1852,1239,1390,1531,1127,980,859,1343,961,1114,920,1506,1186,719,811,1038,274,1116,668,168,1285],
[0,708,1119,2964,1969,438,0,1869,1261,1162,1448,979,686,701,1162,748,911,692,1343,1231,380,598,878,544,1017,502,606,1187],
[0,1231,838,1127,652,1852,1869,0,617,1062,530,973,1399,1200,925,1223,1106,1279,801,666,1603,1326,1062,2126,884,1375,1871,744],
[0,683,416,1739,953,1239,1261,617,0,739,404,490,886,632,582,695,634,741,592,79,1030,760,544,1514,367,780,1270,339],
[0,485,326,1965,864,1390,1162,1062,739,0,533,325,489,536,158,431,278,478,270,794,790,585,376,1628,427,723,1501,402],
[0,757,338,1525,574,1531,1448,530,404,533,0,483,895,748,396,736,599,795,282,483,1134,863,581,1800,431,946,1592,261],
[0,276,144,2007,992,1127,979,973,490,325,483,0,426,281,221,254,144,312,379,525,652,385,101,1383,125,486,1220,231],
[0,220,562,2408,1339,980,686,1399,886,489,895,426,0,296,531,193,296,157,713,901,306,218,346,1188,529,351,1118,655],
[0,81,419,2270,1273,859,701,1200,632,536,748,281,296,0,486,135,257,140,657,632,403,128,183,1108,322,205,966,488],
[0,457,174,1880,813,1343,1162,925,582,158,396,221,531,486,0,414,252,471,183,639,809,566,305,1593,294,688,1440,245],
[0,59,398,2260,1224,961,748,1223,695,431,736,254,193,135,414,0,163,59,596,708,401,155,161,1198,343,293,1078,484],
[0,211,268,2115,1064,1114,911,1106,634,278,599,144,296,257,252,163,0,219,435,665,557,317,119,1356,267,448,1223,363],
[0,63,456,2319,1282,920,692,1279,741,478,795,312,157,140,471,59,219,0,654,750,342,109,217,1151,396,254,1042,541],
[0,636,271,1705,629,1506,1343,801,592,270,282,379,713,657,183,596,435,654,0,663,992,746,474,1762,409,862,1595,279],
[0,690,468,1793,1031,1186,1231,666,79,794,483,525,901,632,639,708,665,750,663,0,1021,758,566,1459,399,763,1209,400],
[0,378,796,2659,1621,719,380,1603,1030,790,1134,652,306,403,809,401,557,342,992,1021,0,277,553,899,720,272,872,877],
[0,110,528,2388,1371,811,598,1326,760,585,863,385,218,128,566,155,317,109,746,758,277,0,284,1043,444,147,934,604],
[0,176,244,2106,1092,1038,878,1062,544,376,581,101,346,183,305,161,119,217,474,566,553,284,0,1290,183,388,1137,325],
[0,1145,1506,3252,1683,274,544,2126,1514,1628,1800,1383,1188,1108,1593,1198,1356,1151,1762,1459,899,1043,1290,0,1380,908,305,1551],
[0,346,138,1949,980,1116,1017,884,367,427,431,125,529,322,294,343,267,396,409,399,720,444,183,1380,0,515,1193,173],
[0,238,622,2462,1478,668,502,1375,780,723,946,486,351,205,688,293,448,254,862,763,272,147,388,908,515,0,788,686],
[0,1020,1328,2998,2163,168,606,1871,1270,1501,1592,1220,1118,966,1440,1078,1223,1042,1595,1209,872,934,1137,305,1193,788,0,1357],
[0,500,97,1784,810,1285,1187,744,339,402,261,231,655,488,245,484,363,541,279,400,877,604,325,1551,173,686,1357,0]
];

let elitismo = 0;
const distanciaMediana=410
const elitismoMaximo=17
maximoGeneraciones=10000
distanciaObjetivo=12000

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
    return shuffleArray(Array.from({ length: 27 }, (_, i) => i + 1));
}

// Evaluar el fitness de un cromosoma
function evaluarCromosoma(cromosoma) {
    elitismo = 0;
    const F1 = [];

    lstArticulos_Ruta=cromosoma.map((index)=>lstArticulosEdos[index]).slice(1,-1)
    
    //console.log(`Ruta: \n${cromosoma}\nRuta de articulos por estado: \n${lstArticulos_Ruta}`)

    for (let i = 0; i < cromosoma.length - 1; i++) {
        F1.push(M[cromosoma[i]][cromosoma[i + 1]]);
    }
    
    // Ajustar elitismo
    for (i = 0; i < elitismoMaximo; i++) {
        if (F1[i] <= distanciaMediana && lstArticulos_Ruta[i]>=mArticulos) {
            elitismo++
        } else {
            break;
        }
    }
    elitismo++

    return [F1, F1.reduce((a, b) => a + b, 0)];
}

// Modificar un cromosoma
function modificarCromosoma(cromosoma, F) {
    const c = [...cromosoma];
    let j = 0;

    lstArticulos_Ruta=cromosoma.map((index)=>lstArticulosEdos[index]).slice(1,-1)

    for (let i = elitismo; i < F.length - 1; i++) {
        if (F[i] <= distanciaMediana  && lstArticulos_Ruta[i]>=mArticulos) {
            const a = c.splice(i, 1)[0];
            c.splice(elitismo, 0, a);
            if (j < 7) j += 2;
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
        bestFitness=nuevoF0;
    }

    return [nuevosCromosomas, nuevoF0];
}

// Obtener la mejor ruta
function obtenerMejorRuta() {
    // Generar cromosomas iniciales
    let cromosomas = crearCromosomas();
    console.log("Ruta inicial:", cromosomas);
    
    // Evaluar el cromosoma inicial
    let [F0, distanciaInicial] = evaluarCromosoma(cromosomas);    
    console.log("Fitness inicial:", F0);
    console.log("Distancia inicial:", distanciaInicial);

    bestScore = distanciaInicial;
    mejorRuta = cromosomas;
    
    generaciones=0
    // Iteraciones del algoritmo genético
    while(bestScore>distanciaObjetivo && generaciones<maximoGeneraciones) {
        [cromosomas, F0] = nextGeneration(cromosomas, F0);
        generaciones+=1
    }

    //console.log("Fitness final:", F0);

    console.log("Generaciones:", generaciones);
    return [mejorRuta, bestScore];
}

function trazarRuta(listaComunidades,lstArticulosEstado,medianaArticulos)
{
    // Ejecución del algoritmo
    lstArticulosEdos=lstArticulosEstado
    //mArticulos=medianaArticulos
    const [rutaGIS, distancia] = obtenerMejorRuta();
    console.log("Mejor ruta:", rutaGIS, "\nMejor fitness:",bestFitness, "\nDistancia total:", distancia);

    // Crear una nueva lista reordenada
    const listaReordenada = rutaGIS.map(index => listaComunidades[index]);

    //Ruta de coordenadas
    const ruta=rutaGIS.map(index => coordenadasComunidades[index])

    return [listaReordenada,ruta,distancia]
}
