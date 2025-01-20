const supabaseUrl = 'https://cqvlrkpsafdazotfprgz.supabase.co';
const supabaseKey = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNxdmxya3BzYWZkYXpvdGZwcmd6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzYzMDUwODUsImV4cCI6MjA1MTg4MTA4NX0.s0HUZhvxsPZxWvv2aAsz5RF_yPmUPdauSbEktDGGE6Y';
const supaClient = supabase.createClient(supabaseUrl, supabaseKey);

async function obtenerDatos(estado) {
    const { data, error } = await supaClient
    .from('historial_padron')
    .select('Periodo,Botas,"Chamarra ligera",Tenis,"Bolsa de Dormir",NumFE')
    .eq('Estado',estado);
    if (error) {
        console.error('Error:', error);
    } else {
        return data
    }
}