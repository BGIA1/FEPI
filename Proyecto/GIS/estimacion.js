
function linearRegression(data) {
    // Calcular los promedios de x y y
    const n = data.length;
    const sumX = data.reduce((acc, point) => acc + point.x, 0);
    const sumY = data.reduce((acc, point) => acc + point.y, 0);
    const avgX = sumX / n;
    const avgY = sumY / n;

    // Calcular los coeficientes de la regresión
    let numerator = 0;
    let denominator = 0;

    for (const point of data) {
        numerator += (point.x - avgX) * (point.y - avgY);
        denominator += (point.x - avgX) ** 2;
    }

    const slope = numerator / denominator; // Pendiente (m)
    const intercept = (avgY - slope * avgX); // Intercepto (b)

    return { slope, intercept };
}

function processRegressions(matrix) {
    // Obtener las claves relevantes de los objetos (excluyendo Periodo y NumFE si no queremos usarlos)
    const keys = Object.keys(matrix[0]).filter(key => key !== "Periodo");

    // Extraer el primer año de cada periodo como X
    const periods = matrix.map(row => parseInt(row.Periodo.split("-")[0], 10));

    // Hacer regresión para cada clave
    const results = {};

    keys.forEach(key => {
        const dataPoints = matrix.map((row, index) => ({
            x: periods[index],           // Primer año como X
            y: parseInt(row[key], 10)    // Convertir los valores a números
        }));

        results[key] = linearRegression(dataPoints);
    });

    return results;
}

// Función para predecir nuevos valores
function predict(x, { slope, intercept }) {
    return parseInt((slope * x + intercept)*1.05);
}

function estimar(año,data)
{
    // Calcular regresiones
    const regressionResults = processRegressions(data);

    // Mostrar resultados
    //console.log("Resultados de regresión para cada elemento:");
    //console.log(regressionResults);

    const estimacionEquipamiento = Object.keys(regressionResults).reduce(
        (acc, key) => ({
            ...acc,
            [key]: predict(año, regressionResults[key])
        }), 
        {}
    );
    estimacionEquipamiento.Gorra=estimacionEquipamiento.NumFE
    estimacionEquipamiento.Mochila=estimacionEquipamiento.NumFE
    estimacionEquipamiento["Chamarra polar"]=estimacionEquipamiento.Botas
    estimacionEquipamiento["Playera tipo polo"]=estimacionEquipamiento.NumFE*2
    //console.log(estimacionEquipamiento)
    return estimacionEquipamiento
}
