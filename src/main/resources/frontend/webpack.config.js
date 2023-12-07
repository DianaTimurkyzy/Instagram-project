const path = require('path');
const { VueLoaderPlugin } = require('vue-loader');

module.exports = {
    entry: './js/index.js',

    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'dist'),
        publicPath: '/dist/'
    },

    mode: 'development',

    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    }
                }
            },
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },
            {
                test: /\.css$/,
                use: [
                    'style-loader',
                    'css-loader'
                ]
            }
        ]
    },

    resolve: {
        alias: {
            'vue$': 'vue/dist/vue.esm-bundler.js'
        },
        extensions: ['*', '.js', '.vue', '.json']
    },

    plugins: [
        new VueLoaderPlugin() //
    ],

    // Starts Node.js server to avoid rebuilding the project after amends
    devServer: {
        static: {
            directory: path.join(__dirname, 'template')
        },
        historyApiFallback: true,
        port: 9000
    }
};
