module.exports = {
    dev:{
        assetsSubDriectory:'static',
       assetsPublicPath:'/',
    },
    devServer:{
        open:'edge',
        proxy:{
            '/zf':{
                target: 'http://124.220.16.147:8011',
                ws:true,
                changeOrigin:true,
                pathRewrite:{
                    '^/zf': ''
                }
            }
        }
    },
    host: '124.220.16.147',
    port: 8010,
    build:{
        publicPath: './',
        outputDir: 'dist',
        runtimeCompiler: false,
        productionSourceMap: false,
    }
}
module.exports = {
    lintOnSave: false
  }