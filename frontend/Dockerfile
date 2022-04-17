FROM node:13-alpine

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

# Since we are adding node_modules as a volume, no need to run npm install inside the container.
# COPY package.json .
# COPY package-lock.json .

# RUN npm install

COPY . .

CMD ["npm", "start"]