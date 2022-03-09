var merge = require("webpack-merge");
var generated = require("./scalajs.webpack.config");

var local = {
  devServer: {
    historyApiFallback: true,
  },
  module: {
    rules: [
      {
        test: /\.css$/,
        use: ["style-loader", "css-loader"],
      },
      {
        test: /\.(ttf|eot|woff|png|glb|svg|jpg)$/,
        use: "file-loader",
      },
      {
        test: /\.(json)$/,
        use: "json-loader",
      },
      {
        test: /\.(eot)$/,
        use: "url-loader",
      },
    ],
  },
};

module.exports = merge(generated, local);
