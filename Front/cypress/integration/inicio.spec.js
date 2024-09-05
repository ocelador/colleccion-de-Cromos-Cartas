describe('Inicio Page', () => {
    it('should display the correct message and carousel', () => {
      cy.visit('/');
      cy.contains('h1', 'MundoCromo');
      cy.get('.carousel-inner').should('exist');
      cy.get('.carousel-item').should('have.length', 4);
    });
  });