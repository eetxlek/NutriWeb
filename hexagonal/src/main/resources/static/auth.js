// auth.js forma parte de la capa de infra del frontend. Esta acoplado a storagelocal y sabe como manejar fetch, sabe manejas JWT.
// objeto auth maneja almacenamiento y verificacion de token JWT. Centraliza logica de autenticacion en un lugar del frontend. Usa fetchconauth para incluir token en peti Rest private
//este script no envia el formulario, gestiona el token y fetch autenticado.
const auth = {
    guardarToken(token) {
      //localstorage no protege de ataques XSS inyeccsion de script, el token es robable. más seguridad seria HttpOnly coockie pero cambia el flujo bastante.
      localStorage.setItem('authToken', token);
      // Decodificar y guardar exp (si necesitas)
      const tokenData = JSON.parse(atob(token.split('.')[1]));
      localStorage.setItem('tokenExpiracion', new Date(tokenData.exp * 1000));
    },
    
    obtenerToken() {
      return localStorage.getItem('authToken');
    },
    //corregio para que no tenga que guardar tokenexpiracion por separado
    estaAutenticado() {
        const token = this.obtenerToken();
        if (!token) return false;
    
        try {
            const payload = JSON.parse(atob(token.split('.')[1]));
            const expiracion = new Date(payload.exp * 1000);
            return expiracion > new Date();
        } catch (e) {
            return false;
        }
    },    
    
    cerrarSesion() {
      localStorage.removeItem('authToken');
      localStorage.removeItem('tokenExpiracion');
      window.location.href = '/login';
    },
    
    fetchConAutenticacion: async function(url, opciones = {}) {
      if (!this.estaAutenticado()) {
        this.cerrarSesion();
        return;
      }
      
      const headers = {
        ...opciones.headers,
        'Authorization': `Bearer ${this.obtenerToken()}`
      };
      
      return fetch(url, { ...opciones, headers });
    }
  };

  /*
  // ejemplo simple que hace llamada htttp, recoge respuesta y almacena el token en storage
fetch('/login', {
  method: 'POST',
  body: JSON.stringify({ username, password }),
  headers: { 'Content-Type': 'application/json' }
})
.then(res => res.json())
.then(data => {
  const token = data.token;
  localStorage.setItem('authToken', token);
});
*/