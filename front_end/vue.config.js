module.exports = {
    devServer: {
        port: 8000
    },
    css: {
        loaderOptions: {
            less: {
                modifyVars: {
                    'primary-color': '#aa82f5',
                    'link-color': '#aa82f5',
                    'border-radius-base': '2px',
                },
                javascriptEnabled: true,
            },
        },
    },
}