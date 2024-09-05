describe('Albumes Page', () => {
    it('should display the list of albums', () => {
      cy.visit('/albumes');
      cy.contains('h1', 'Gestión de Álbumes');
      cy.get('.list-group-item').should('have.length.greaterThan', 0);
    });
  
    it('should create a new album', () => {
      cy.visit('/albumes');
      cy.get('#nombre', { timeout: 10000 }).should('be.visible').type('Nuevo Álbum'); // Aumentar el tiempo de espera a 10 segundos
      cy.get('#descripcion').type('Descripción del nuevo álbum');
      cy.get('#file').attachFile('/home/curso003/Desktop/colleccion-de-Cromos-Cartas/Back/coleccionCartas/images/pokemon.jpg'); // Asegúrate de tener un archivo de imagen en la ruta especificada
      cy.get('.btn-primary').click();
      cy.get('.list-group-item').should('contain', 'Nuevo Álbum');
    });
  });