module.exports = {
    devServer:{
        open:'edge',
        proxy:{
            '/zf':{
                target: 'http://localhost:8081',
                // ws:true,
                changeOrigin:true,
                pathRewrite:{
                    '^/zf': ''
                }
            }
        }
    }
}
module.exports = {
    lintOnSave: false
  }