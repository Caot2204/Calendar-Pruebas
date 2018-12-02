/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendariopruebas;

import java.util.Scanner;

/**
 *
 * @author Carlos Onorio
 */
public class CalendarioPruebas {

    private static String fecha;
    private static int dia;
    private static int mes;
    private static int anio;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opcion = "";

        while (!opcion.equals("7")) {
            System.out.println("1. Leer fecha");
            System.out.println("2. Siguiente dia");
            System.out.println("3. Dia de la semana");
            System.out.println("4. Zodiaco");
            System.out.println("5. Ultimo Memorial Day");
            System.out.println("6. Ultimo viernes 13");
            System.out.println("7. Salir");

            opcion = scanner.next();

            switch (opcion) {
                case "1":
                    leerFecha();
                    break;
                case "2":
                    System.out.println("Siguiente dia: " + determinarSiguienteDia());
                    break;
                case "3":
                    System.out.println("Dia de la semana: " + determinarDiaSemana(fecha));
                    break;
                case "4":
                    System.out.println("Signo del zodiaco: " + determinarZodiaco());
                    break;
                case "5":
                    System.out.println("Ultimo Memorial Day: " + determinarMemorialDay());
                    ;
                    break;
                case "6":
                    System.out.println("Ultimo viernes 13: " + determinarViernesTrece());
                    ;
                    break;
                case "7":
                    break;
                default:
                    System.out.println("Opcion invalida...");
            }
        }

    }

    private static void leerFecha() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("\nIngrese la fecha en formato mm-dd-yyyy: ");
            fecha = scanner.next();
        } while (!validarFecha());
    }

    private static boolean validarFecha() {
        boolean fechaValida = false;
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] valores = fecha.split("-");
        dia = Integer.parseInt(valores[1]);
        mes = Integer.parseInt(valores[0]);
        anio = Integer.parseInt(valores[2]);

        if ((mes >= 1 && mes <= 12) && (anio > 0)) {
            if (dia > 0) {
                if (mes == 2) {
                    if (esBisiesto() && dia <= 29) {
                        fechaValida = true;
                    } else if (!esBisiesto() && dia <= 28) {
                        fechaValida = true;
                    } else {
                        System.out.println("Dia invalido para febrero, puede que el anio no sea bisiesto o ingreso un dia mayor a 29");
                    }
                } else {
                    if (dia <= diasPorMes[mes - 1]) {
                        fechaValida = true;
                    } else {
                        System.out.println("Dia invalido para el mes " + mes);
                    }
                }
            } else {
                System.out.println("El dia no puede ser menor a 1");
            }
        } else {
            System.out.println("El mes o el anio es invalido");
        }

        return fechaValida;
    }

    private static String determinarSiguienteDia() {
        String[] valores = fecha.split("-");
        dia = Integer.parseInt(valores[1]);
        mes = Integer.parseInt(valores[0]);
        anio = Integer.parseInt(valores[2]);

        switch (mes) {
            case 1:
                calcularDiaMesConTreintaYUnDias();
                break;
            case 2:
                if ((dia == 28 && esBisiesto()) || dia < 28) {
                    dia++;
                } else {
                    dia = 1;
                    mes++;
                }
                break;
            case 3:
                calcularDiaMesConTreintaYUnDias();
                break;

            case 4:
                calcularDiaMesConTreintaDias();
                break;

            case 5:
                calcularDiaMesConTreintaYUnDias();
                break;

            case 6:
                calcularDiaMesConTreintaDias();
                break;

            case 7:
                calcularDiaMesConTreintaYUnDias();
                break;

            case 8:
                calcularDiaMesConTreintaYUnDias();
                break;

            case 9:
                calcularDiaMesConTreintaDias();
                break;

            case 10:
                calcularDiaMesConTreintaYUnDias();
                break;

            case 11:
                calcularDiaMesConTreintaDias();
                break;

            case 12:
                if (dia == 31) {
                    dia = 1;
                    mes = 1;
                    anio++;
                } else {
                    calcularDiaMesConTreintaYUnDias();
                }
                break;
        }

        return obtenerFechaString();
    }

    private static String determinarDiaAnterior(String fecha) {
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] valores = fecha.split("-");
        dia = Integer.parseInt(valores[1]);
        mes = Integer.parseInt(valores[0]);
        anio = Integer.parseInt(valores[2]);

        if (dia > 1) {
            dia--;
        } else if (dia == 1) {
            mes--;

            if (mes == 0) {
                mes = 12;
                anio--;
                dia = diasPorMes[mes - 1];
            } else if (mes == 2 && esBisiesto()) {
                dia = 29;
            } else {
                dia = diasPorMes[mes - 1];
            }
        }

        return obtenerFechaString();
    }

    private static String determinarDiaSemana(String fecha) {
        String[] diasDeSemana = {"Sabado", "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        String[] valores = fecha.split("-");
        dia = Integer.parseInt(valores[1]);
        mes = Integer.parseInt(valores[0]);
        anio = Integer.parseInt(valores[2]);

        int k = anio % 100;
        int j = anio / 100;

        int diaObtenido = (dia + ((13 * (mes + 1)) / 5) + k + (k / 4) + (j / 4) + (5 * j)) % 7;

        return diasDeSemana[diaObtenido];
    }

    private static String determinarZodiaco() {
        String[] valores = fecha.split("-");
        String signo = "";
        dia = Integer.parseInt(valores[1]);
        mes = Integer.parseInt(valores[0]);

        if ((dia >= 21 && mes == 1) || (dia <= 19 && mes == 2)) {
            signo = "Acuario";
        } else if ((dia >= 20 && mes == 2) || (dia <= 20 && mes == 3)) {
            signo = "Piscis";
        } else if ((dia >= 21 && mes == 3) || (dia <= 20 && mes == 4)) {
            signo = "Aries";
        } else if ((dia >= 21 && mes == 4) || (dia <= 21 && mes == 5)) {
            signo = "Tauro";
        } else if ((dia >= 22 && mes == 5) || (dia <= 21 && mes == 6)) {
            signo = "Geminis";
        } else if ((dia >= 22 && mes == 6) || (dia <= 22 && mes == 7)) {
            signo = "Cancer";
        } else if ((dia >= 23 && mes == 7) || (dia <= 23 && mes == 8)) {
            signo = "Leo";
        } else if ((dia >= 24 && mes == 8) || (dia <= 23 && mes == 9)) {
            signo = "Virgo";
        } else if ((dia >= 24 && mes == 9) || (dia <= 23 && mes == 10)) {
            signo = "Libra";
        } else if ((dia >= 24 && mes == 10) || (dia <= 22 && mes == 11)) {
            signo = "Escorpion";
        } else if ((dia >= 23 && mes == 11) || (dia <= 21 && mes == 12)) {
            signo = "Sagitario";
        } else if ((dia >= 22 && mes == 12) || (dia <= 20 && mes == 1)) {
            signo = "Capricornio";
        }

        return signo;
    }

    private static String determinarMemorialDay() {
        String memorialDay = fecha;

        while (true) {
            memorialDay = determinarDiaAnterior(memorialDay);
            String[] valores = memorialDay.split("-");
            String nombreDiaActual = determinarDiaSemana(memorialDay);
            int mesActual = Integer.parseInt(valores[0]);

            if (mesActual == 5 && nombreDiaActual.equals("Lunes")) {
                break;
            }

        }

        return memorialDay;
    }

    private static String determinarViernesTrece() {
        String fechaViernesTrece = fecha;

        while (true) {
            fechaViernesTrece = determinarDiaAnterior(fechaViernesTrece);
            String[] valores = fechaViernesTrece.split("-");
            String nombreDiaActual = determinarDiaSemana(fechaViernesTrece);
            int diaActual = Integer.parseInt(valores[1]);

            if (diaActual == 13 && nombreDiaActual.equals("Viernes")) {
                break;
            }
        }

        return fechaViernesTrece;
    }

    private static boolean esBisiesto() {
        boolean bisiesto = false;
        String[] valores = fecha.split("-");
        int anio = Integer.parseInt(valores[2]);
        if ((anio % 100 == 0 && anio % 400 == 0) || anio % 4 == 0) {
            bisiesto = true;
        }
        return bisiesto;
    }

    private static void calcularDiaMesConTreintaYUnDias() {
        if (dia < 31) {
            dia++;
        } else if (dia == 31) {
            dia = 1;
            mes++;
        }
    }

    private static void calcularDiaMesConTreintaDias() {
        if (dia < 30) {
            dia++;
        } else if (dia == 30) {
            dia = 1;
            mes++;
        }
    }

    private static String obtenerFechaString() {
        String diaString = Integer.toString(dia);
        String mesString = Integer.toString(mes);
        String anioString = Integer.toString(anio);

        if (dia < 10) {
            diaString = "0" + diaString;
        }
        if (mes < 10) {
            mesString = "0" + mesString;
        }
        if (anio < 10) {
            anioString = "000" + anioString;
        } else if (anio < 100) {
            anioString = "00" + anioString;
        } else if (anio < 1000) {
            anioString = "0" + anioString;
        }
        return mesString + "-" + diaString + "-" + anioString;
    }

}
