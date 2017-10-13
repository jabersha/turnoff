window.onload = function() {
  Particles.init({
    options
  });
};

window.onload = function() {
  Particles.init({
    selector: '#myCanvas',
    color: '#9a004e',
    maxParticles: 110,
    connectParticles: true,
    speed: 1,
    minDistance: 100,
    SizeVariations: 100,
    responsive: [{
        breakpoint: 800,
        options: {
          maxParticles: 70
        }
      }, {
        breakpoint: 600,
        options: {
          maxParticles: 40
        }
      },
      {
        breakpoint: 300,
        options: {
          maxParticles: 20
        }
      }
    ]
  });
};
