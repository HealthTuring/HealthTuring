import { AfterViewInit, Component, ElementRef, HostListener, ViewChild } from '@angular/core';
import { RouterLink } from '@angular/router';
import Chart, { registerables } from 'chart.js/auto';
Chart.register(...registerables);

@Component({
  imports: [RouterLink],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent implements AfterViewInit {

  @ViewChild('chartCanvas', { static: false }) chartCanvas!: ElementRef<HTMLCanvasElement>;

  constructor(private el: ElementRef) {}

  ngAfterViewInit() {
    this.checkPanels();
    this.checkOpinions();
    this.buildChart();
  }

  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.checkPanels();
    this.checkOpinions();
  }

  checkPanels() {
    const panels = this.el.nativeElement.querySelectorAll('.services__item');
    panels.forEach((panel: HTMLElement, i: number) => {
      const rect = panel.getBoundingClientRect();
      if (rect.top < window.innerHeight) {
        panel.classList.add('panel-fase-in');
        panel.style.transitionDelay = `${i * 0.15}s`;
      } else {
        panel.classList.remove('panel-fase-in');
        panel.style.transitionDelay = '0s';
      }
    });
  }

  checkOpinions() {
    const opinions = this.el.nativeElement.querySelectorAll('.reviews__item');
    opinions.forEach((opinion: HTMLElement, i: number) => {
      const rect = opinion.getBoundingClientRect();
      if (rect.top < window.innerHeight) {
        opinion.classList.add('reviews__item--visible');
        opinion.style.transitionDelay = `${i * 0.15}s`;
      } else {
        opinion.classList.remove('reviews__item--visible');
        opinion.style.transitionDelay = '0s';
      }
    });
  }

  buildChart() {
  new Chart(this.chartCanvas.nativeElement, {
      type: 'doughnut',
      data: {
        labels: ['Citas concertadas', 'Tratamientos activos'],
        datasets: [{
          label: 'Totales',
          data: [27, 72],
          backgroundColor: ['#2DABB9', '#1e2939']
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          legend: {
            display: false
          },
          title: {
            display: true,
            text: 'Resumen general'
          }
        }
      }
    });
  }

}
