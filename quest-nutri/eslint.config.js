const tsPlugin = require('@typescript-eslint/eslint-plugin')

module.exports = [
    {
        ignores: ["node_modules/**"]
    },
    {
        files: ["**/*.ts"],
        languageOptions: {
            ecmaVersion: "latest",
            sourceType: "module",
            parser: require("@typescript-eslint/parser")
        },
        plugins: {
            "@typescript-eslint": tsPlugin
        },
        rules: {
            quotes: ["error", "single", { avoidEscape: true, allowTemplateLiterals: true }],
            semi: ["error", "never"],
            indent: ["error", "tab"],
            "no-tabs": 0,
            "@typescript-eslint/explicit-module-boundary-types": "off"
        }
    }
]
