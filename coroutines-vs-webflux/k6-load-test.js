import http from 'k6/http';

export const options = {
    scenarios: {
        contacts: {
          executor: 'ramping-vus',
          startVUs: 20,
          stages: [
            { duration: '60s', target: 800 },
            { duration: '10s', target: 0 },
          ],
          gracefulRampDown: '10s',
        },
      },
};

export default function () {
  http.get('http://localhost:8080/tasks/1');
}