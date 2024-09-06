describe('Cromos Page', () => {
  beforeEach(() => {
    cy.visit('/cromos');
  });

  it('should display the list of cromos', () => {
    cy.url().should('include', '/cromos');
    cy.get('h1').should('be.visible').and('contain', 'Gestión de Cromos');
    cy.get('.list-group-item').should('have.length.greaterThan', 0);
  });

  it('should create a new cromo', () => {
    cy.get('#nombre').should('be.visible').type('Nuevo Cromo');
    cy.get('#descripcion').type('Descripción del nuevo cromo');
    cy.get('#anio').type('2023');
    cy.get('#valor').type('100');
    cy.get('#rareza').type('Raro');
    cy.get('.btn-primary').click();
    cy.get('.list-group-item').should('contain', 'Nuevo Cromo');
  });

  it('should update an existing cromo', () => {
    cy.get('.list-group-item').first().within(() => {
      cy.get('.btn-success').click(); // Seleccionar el primer cromo
    });
    cy.get('#nombre').clear().type('Cromo Actualizado');
    cy.get('#descripcion').clear().type('Descripción actualizada');
    cy.get('#anio').clear().type('2024');
    cy.get('#valor').clear().type('200');
    cy.get('#rareza').clear().type('Muy Raro');
    cy.get('.btn-warning').click(); // Hacer clic en el botón "Actualizar Cromo"
    cy.get('.list-group-item').should('contain', 'Cromo Actualizado');
  });

  it('should delete an existing cromo', () => {
    cy.get('.list-group-item').first().within(() => {
      cy.get('.btn-danger').click(); // Hacer clic en el botón "Eliminar"
    });
    cy.get('.modal-container').should('be.visible');
    cy.get('.modal-footer .btn-primary').click(); // Confirmar eliminación
    cy.get('.list-group-item').should('not.contain', 'Cromo Actualizado');
  });
});