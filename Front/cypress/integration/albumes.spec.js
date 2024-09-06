describe('Albums Page', () => {
  beforeEach(() => {
    cy.visit('/albums');
  });

  it('should display the list of albums', () => {
    cy.url().should('include', '/albums');
    cy.get('h1').should('be.visible').and('contain', 'Gestión de Álbumes');
    cy.get('.list-group-item').should('have.length.greaterThan', 0);
  });

  it('should create a new album', () => {
    cy.get('#nombre').should('be.visible').type('Nuevo Álbum');
    cy.get('#descripcion').type('Descripción del nuevo álbum');
    cy.get('#file').attachFile('path/to/your/image.jpg'); // Asegúrate de tener un archivo de imagen en la ruta especificada
    cy.get('.btn-primary').click();
    cy.get('.list-group-item').should('contain', 'Nuevo Álbum');
  });

  it('should update an existing album', () => {
    cy.get('.list-group-item').first().within(() => {
      cy.get('.btn-info').click(); // Seleccionar el primer álbum
    });
    cy.get('#nombre').clear().type('Álbum Actualizado');
    cy.get('#descripcion').clear().type('Descripción actualizada');
    cy.get('.btn-warning').click(); // Hacer clic en el botón "Actualizar Álbum"
    cy.get('.list-group-item').should('contain', 'Álbum Actualizado');
  });

  it('should delete an existing album', () => {
    cy.get('.list-group-item').first().within(() => {
      cy.get('.btn-danger').click(); // Hacer clic en el botón "Eliminar"
    });
    cy.get('.modal-container').should('be.visible');
    cy.get('.modal-footer .btn-primary').click(); // Confirmar eliminación
    cy.get('.list-group-item').should('not.contain', 'Álbum Actualizado');
  });
});