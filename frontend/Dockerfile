FROM node:13-alpine

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY package.json .
COPY package-lock.json .

RUN npm install
RUN npm install react-scripts
RUN npm install axios
RUN npm install react-bootstrap

COPY . .

CMD ["npm", "start"]