// auth.js forma parte de la capa de infra del frontend. Esta acoplado a storagelocal y sabe como manejar fetch, sabe manejas JWT.
// objeto auth maneja almacenamiento y verificacion de token JWT. Centraliza logica de autenticacion en un lugar del frontend. Usa fetchconauth para incluir token en peti Rest private
//este script no envia el formulario, gestiona el token y fetch autenticado.

console.log("auth.js cargado");

export const auth = {
  guardarToken(token) {
    if (!token) {
      console.error('El token es inválido o está vacío');
      return;
    }
    // Verificar token estructura correcta
    if (typeof token === 'string' && token.split('.').length === 3) {
      localStorage.setItem('authToken', token);        // Almacenar el token en localStorage
      // Decodificar y guardar exp (si necesitas)
      const tokenData = JSON.parse(atob(token.split('.')[1])); // Decodificación del payload
      // console.log(tokenData.toString());
      localStorage.setItem('tokenExpiracion', new Date(tokenData.exp * 1000));
    } else {
      console.error('El token tiene un formato incorrecto');
    }
  },

  obtenerToken() {

    //console.log("Token recuperado del localStorage:", token);
    return localStorage.getItem('authToken');
  },
  //corregio para que no tenga que guardar tokenexpiracion por separado
  estaAutenticado() {
    const token = this.obtenerToken();
    if (!token || token.split('.').length !== 3) return false;

    try {
      const payload = JSON.parse(atob(token.split('.')[1]));  // Decodificar el token
      const expiracion = new Date(payload.exp * 1000); // Verificar la expiración
      console.log("Expiración del token:", expiracion);
      return expiracion > new Date();
    } catch (e) {
      console.error("Error al decodificar el token:", e);
      return false;
    }
  },

  cerrarSesion() {
    localStorage.removeItem('authToken');
    localStorage.removeItem('tokenExpiracion');
    window.location.href = '/login';
  },

  fetchConAutenticacion: async function (url, opciones = {}) {
    console.log(`[Auth] Iniciando fetchConAutenticacion para URL: ${url}`);
    const token = this.obtenerToken();
    console.log("Token que se va a enviar:", token);

    if (!this.estaAutenticado()) {
      this.cerrarSesion();
      return new Response(null, { status: 401 });
    }
    // Agregar header de autorización a lo que ya exista
    // Configurar las opciones para el fetch
    const opcionesFetch = {
      ...opciones,
      headers: {
        ...(opciones.headers || {}),
        'Authorization': `Bearer ${token}`
      }
    };
    console.log("[Auth] Opciones de fetch:", JSON.stringify({
      method: opcionesFetch.method,
      headers: Object.keys(opcionesFetch.headers),
      bodyLength: opcionesFetch.body ? opcionesFetch.body.length : 0
    }));
    // Hace peti
    return fetch(url, opcionesFetch);
  }
};

window.auth = auth; //expone auth al window global

