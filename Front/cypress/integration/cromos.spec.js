describe('Cromos Page', () => {
    it('should display the list of cromos', () => {
      cy.visit('/cromos');
      cy.get('h1', { timeout: 10000 }).should('be.visible').and('contain', 'Gestión de Cromos'); // Aumentar el tiempo de espera a 10 segundos
      cy.get('.list-group-item').should('have.length.greaterThan', 0);
    });
  
    it('should create a new cromo', () => {
      cy.visit('/cromos');
      cy.get('#nombre', { timeout: 10000 }).should('be.visible').type('Nuevo Cromo'); // Aumentar el tiempo de espera a 10 segundos
      cy.get('#descripcion').type('Descripción del nuevo cromo');
      cy.get('#anio').type('2023');
      cy.get('#valor').type('100');
      cy.get('#rareza').type('Raro');
      cy.get('.btn-primary').click();
      cy.get('.list-group-item').should('contain', 'Nuevo Cromo');
    });
  });