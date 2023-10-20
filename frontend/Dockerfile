FROM node:18.12.1

WORKDIR /app

COPY ./src /app/src
COPY ./public /app/public
COPY ./package.json /app/package.json
COPY ./.env.docker /app/.env
COPY ./jsconfig.json /app/jsconfig.json

RUN npm install

RUN npm run build
RUN npm install -g serve

CMD ["serve", "-s", "build"]
