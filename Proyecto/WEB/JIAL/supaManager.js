const supabaseUrl = 'https://cqvlrkpsafdazotfprgz.supabase.co';
const supabaseKey = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNxdmxya3BzYWZkYXpvdGZwcmd6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzYzMDUwODUsImV4cCI6MjA1MTg4MTA4NX0.s0HUZhvxsPZxWvv2aAsz5RF_yPmUPdauSbEktDGGE6Y';
const supaClient = supabase.createClient(supabaseUrl, supabaseKey);

async function obtenerDatos(estado) {
    const { data, error } = await supaClient
    .from('historial_padron')
    .select('Periodo,Botas,Chamarra_ligera,Tenis,Bolsa_de_Dormir,NumFE')
    .eq('Estado',estado);
    if (error) {
        console.error('Error al consultar historial', error);
        return { success: false, message: 'No se pudo consultar el historial' };
    } else
    {
        return data;
    }
}

async function registrarEnvio(Periodo, Estado, Gorra, Mochila, Botas, Tenis, Total, NumFE, Playera_polo, Chamarra_polar, Chamarra_ligera, Bolsa_de_Dormir) {
    // Verificar si el registro ya existe
    const { data: existingData, error: selectError } = await supaClient
        .from('historial_padron')
        .select('id')
        .eq('Periodo', Periodo)

    if (selectError) {
        console.error('Error al consultar historial', selectError);
        return { success: false, message: 'No se pudo consultar el historial' };
    }

    if (existingData.length > 0) {
        // El registro ya existe
        return { success: false, message: 'El registro ya existe en el historial de envios'};
    } else {
        // Insertar el nuevo registro
        const { data, error } = await supaClient
            .from('historial_padron')
            .insert({
                Periodo: Periodo,
                Estado: Estado,
                Gorra: Gorra,
                Mochila: Mochila,
                Botas: Botas,
                Tenis: Tenis,
                Total: Total,
                NumFE: NumFE,
                estatus: "Enviado",
                Playera_tipo_polo: Playera_polo,
                Chamarra_polar: Chamarra_polar,
                Chamarra_ligera: Chamarra_ligera,
                Bolsa_de_Dormir: Bolsa_de_Dormir
            })
            .select();

        if (error) {
            console.error('Error al registrar envio', error);
            return { success: false, message: 'No se pudo generar el envio, intente de nuevo mas tarde' };
        } else {
            return { success: true, message: 'Envio de recursos generado con éxito' };
        }
    }
}
